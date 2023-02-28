package com.lec.spring.service;

import com.lec.spring.domain.FileDTO;
import com.lec.spring.domain.RequestBook;
import com.lec.spring.domain.User;
import com.lec.spring.repository.FileRepository;
import com.lec.spring.repository.RequestBookRepository;
import com.lec.spring.repository.UserRepository;
import com.lec.spring.util.U;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RequestBookService {
    @Value("${app.upload.path}") //경로 값을 주입
    private String uploadDir;
    private RequestBookRepository requestBookRepository;
    private UserRepository userRepository;
    private FileRepository fileRepository; //대부분 여기서 사용

    @Autowired
    public RequestBookService(SqlSession sqlSession){  // MyBatis 가 생성한 SqlSession 빈(bean) 객체 주입
        requestBookRepository = sqlSession.getMapper(RequestBookRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);
        fileRepository = sqlSession.getMapper(FileRepository.class);
        System.out.println("RequestBookService() 생성");
    }
    public int requestbook(RequestBook requestBook, Map<String, MultipartFile> files //첨부 파일들
    ){//글만 작성에서 첨부파일 정보 추가
        // 현재 로그인한 작성자 정보
        User user = U.getLoggedUser();

        // 위 정보는 session 의 정보이고, 일단 DB에서 다시 읽어옴
        user = userRepository.findById(user.getId());
        requestBook.setUser(user);  // 글 작성자 세팅

        int cnt = requestBookRepository.save(requestBook);//위에거가 없으면 user정보 setting이 안됨
        //첨부파일 추가
        addFiles(files, requestBook.getId()); //파일과 글의 아이디 값을 넘김

        return cnt;
    }
    //특정 글(id) 첨부파일(들) 추가
    private void addFiles(Map<String , MultipartFile> files, Long id){ // 파일 첨부는 Multipart request 해야함
        if(files != null){
            for(Map.Entry<String, MultipartFile> e :files.entrySet()){ //Map 에 있는 정보를 뽑아옴 entryset 사용

                //name="ufile##" 인경우만 첨부파일 등록(이유, 다른 웹에디터와 섞이지 않도록 ..)
                if(!e.getKey().startsWith("upfile")) continue;//upfile로 시작하는지 확인



                //첨부파일 정보 출력
                System.out.println("\n첨부파일 정보: "+e.getKey()); //name 값
                U.printFileInfo(e.getValue());
                System.out.println();

                //물리적인 파일 저장 여기서 중복된 이름 처리
                FileDTO file = upload(e.getValue());

                //성공하면 DB에도 저장
                if(file != null){
                    file.setRequest_id(id); //FK 설정
                    fileRepository.save(file); //insert 여기로 옴
                }
            }
        }
    }//end addFiles()
    //물리적으로 파일 저장. 중복된 이름 rename 처리
    private FileDTO upload(MultipartFile multipartFile){
        FileDTO attachment = null;

        // 담긴 파일이 없으면 pass~
        String originalFilename = multipartFile.getOriginalFilename();
        if(originalFilename == null || originalFilename.length() == 0) return null;

        // 원본 파일 명
        //                  org.springframework.util.StringUtils
        String sourceName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        // 저장될 파일 명
        String fileName = sourceName;

        //파일이 중복되는지 확인.
        File file = new File(uploadDir + File.separator + sourceName);
        if(file.exists()){ // 이미 존재하는 파일명, 중복된다면 다른 이름으로 변경하여 파일저장
            int pos = fileName.lastIndexOf(".");
            if(pos > -1) { // 확장자가 있는 경우
                String name = fileName.substring(0, pos);  // 파일'이름'
                String ext = fileName.substring(pos + 1);  // 파일'확장명'

                // 중복방지를 위한 새로운 이름 (현재시간 ms) 를 파일명에 추가
                fileName = name + "_" + System.currentTimeMillis() + "." + ext;
            } else {
                fileName += "_" + System.currentTimeMillis();
            }

        }
        // 저장할 피일명
        System.out.println("fileName: " + fileName);

        Path copyOfLocation = Paths.get(new File(uploadDir+File.separator+fileName).getAbsolutePath()); //실제 저장되는 경로 복사
        System.out.println(copyOfLocation);

        try {
            // inputStream을 가져와서
            // copyOfLocation (저장위치)로 파일을 쓴다.
            // copy의 옵션은 기존에 존재하면 REPLACE(대체한다), 오버라이딩 한다

            // java.nio.file.Files
            Files.copy(
                    multipartFile.getInputStream(),//Stream 뽑는다는게 중요
                    copyOfLocation,
                    StandardCopyOption.REPLACE_EXISTING   // 기존에 존재하면 덮어쓰기
            );
        } catch (IOException e) {
            e.printStackTrace();
            // 예외처리는 여기서.
            //throw new FileStorageException("Could not store file : " + multipartFile.getOriginalFilename());
        }

        attachment = FileDTO.builder()
                .file(fileName) //저장된 이름
                .source(sourceName)//원본이름
                .build();

        return attachment; //리턴된것이 INSERT 쪽으로 감
    }//end upload

    // 특정 id 의 글 조회
    // 트랜잭션 처리
    // 1. 조회수 증가 (UPDATE)
    // 2. 글 읽어오기 (SELECT)
    @Transactional
    public List<RequestBook> detail(long id) {
        List<RequestBook> list = new ArrayList<>();

        RequestBook requestBook = requestBookRepository.findById(id);

        if(requestBook != null){

            //첨부파일(들) 정보 가져오기
            List<FileDTO> fileList = fileRepository.findByWrite(requestBook.getId());
            setImage(fileList);//이미지 파일 여부 세팅
//            write.setFiles(fileList);
            requestBook.setFileList(fileList);
            list.add(requestBook);
        }

        return list;
    }
    private void setImage(List<FileDTO> fileList) {
        // upload 실제 물리적인 경로
        String realPath = new File(uploadDir).getAbsolutePath();

        for(FileDTO fileDto : fileList) {
            BufferedImage imgData = null;
            File f = new File(realPath, fileDto.getFile());  // 첨부파일에 대한 File 객체
            try {
                imgData = ImageIO.read(f);
                // ※ ↑ 파일이 존재 하지 않으면 IOException 발생한다
                //   ↑ 이미지가 아닌 경우는 null 리턴
            } catch (IOException e) {
                System.out.println("파일존재안함: " + f.getAbsolutePath() + " [" + e.getMessage() + "]");
            }

            if(imgData != null) fileDto.setImage(true); // 이미지 여부 체크
        } // end for
    }

    public List<RequestBook> list(){
        return requestBookRepository.findAll();
    }

    // 특정 id 의 글 읽어오기
    // 조회수 증가 없음
    public List<RequestBook> selectById(long id) {
        List<RequestBook> list = new ArrayList<>();

        RequestBook requestBook = requestBookRepository.findById(id);

        if(requestBook != null){
            // 첨부파일 정보 가져오기
            List<FileDTO> fileList = fileRepository.findByWrite(requestBook.getId());
            setImage(fileList);   // 이미지 파일 여부 세팅
//            requestBook.setFiles(fileList);
            requestBook.setFileList(fileList);

            list.add(requestBook);
        }

        return list;
    }

    //Controller 에서 추가된 매개변수를  서비스에서도 추가해줘야함
    public int update(RequestBook requestBook,
                      Map<String, MultipartFile> files//새로운 추가된 첨부파일들
            , Long[] delfile)//삭제될 첨부파일들
    {
        int result = 0;

        result = requestBookRepository.update(requestBook);

        //첨부파일 추가
        addFiles(files, requestBook.getId());

        //삭제할 첨부파일들은 삭제하기
        if(delfile != null){
            for(Long fileId : delfile){
                FileDTO file = fileRepository.findById(fileId);//Db에서 꺼내옴
                if(file !=null){//존재하는지 확인, 존재한다면
                    delFile(file); //물리적으로 삭제
                    fileRepository.delete(file); //DB 에서도 삭제
                }
            }

        }

        return result;
    }//end update


    public int deleteById(long id){//특정 아이디의 글  삭제
        int result = 0;

        RequestBook requestBook = requestBookRepository.findById(id);
        if(requestBook != null) {
            //물리적으로 저장된 첨부파일(들)삭제 이거 먼저 하고 삭제해야함
            List<FileDTO> fileList = fileRepository.findByWrite(id);
            if(fileList != null && fileList.size() > 0) {//DB삭제 하는 거 없음
                for (FileDTO file : fileList) {
                    delFile(file);//물리적 파일 삭제
                }
            }
            //글 삭제(참조하는 첨부파일, 댓글 등도 같이 삭제 될 것이다. ON DELETE CASCADE)
            result = requestBookRepository.delete(requestBook);//여기서  DB가 같이 삭제됨
        }

        return result;
    }
    //특정 첨부파일(id)를 물리적으로 삭제
    private void delFile(FileDTO file) {
        String saveDirectory = new File(uploadDir).getAbsolutePath();//id 가져옴

        File f = new File(saveDirectory, file.getFile()); // 물리적으로 저장된 파일들이 삭제 대상
        System.out.println("삭제시도--> " + f.getAbsolutePath());

        if (f.exists()) {
            if (f.delete()) { // 삭제!
                System.out.println("삭제 성공");
            } else {
                System.out.println("삭제 실패");
            }
        } else {
            System.out.println("파일이 존재하지 않습니다.");
        } // end if
    }



}
