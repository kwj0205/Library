const content = document.getElementById("content");
const img = document.getElementById("img");
const k_map = document.getElementById("k_map");
const b1 = document.getElementById("b1");
const b2 = document.getElementById("b2");
const b3 = document.getElementById("b3");

function c() {
  content.style.display = "block";
  img.style.display = "none";
  k_map.style.display = "none";
  b1.style.backgroundColor = "rgb(61, 86, 86)";
  b2.style.backgroundColor = "rgba(165, 163, 167, 0.1)";
  b3.style.backgroundColor = "rgba(165, 163, 167, 0.1)";
  b1.style.color = "white";
  b2.style.color = "black";
  b3.style.color = "black";
}
function i() {
  content.style.display = "none";
  img.style.display = "block";
  k_map.style.display = "none";
  b1.style.backgroundColor = "rgba(165, 163, 167, 0.1)";
  b2.style.backgroundColor = "rgb(61, 86, 86)";
  b3.style.backgroundColor = "rgba(165, 163, 167, 0.1)";
  b1.style.color = "black";
  b2.style.color = "white";
  b3.style.color = "black";

}
function k() {
  content.style.display = "none";
  img.style.display = "none";
  k_map.style.display = "block";
  b1.style.backgroundColor = "rgba(165, 163, 167, 0.1)";
  b2.style.backgroundColor = "rgba(165, 163, 167, 0.1)";
  b3.style.backgroundColor = "rgb(61, 86, 86)";
  b1.style.color = "black";
  b2.style.color = "black";
  b3.style.color = "white";
}

