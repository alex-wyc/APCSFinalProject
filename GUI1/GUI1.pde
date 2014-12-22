PImage imageFoo;

void setup() {
  size(1600, 900);
  imageFoo = loadImage("gif1.gif");
}

void draw() {
  image(imageFoo, 0, 0);
  print("HELLO, WORLD");
}
