package mypractices;
class Box {
    double width;
    double height;
    double depth;
}
//object declaration
class BoxDemo {
    public static void main (
        String args[]) {
            Box mybox = new Box();
            double vol;
        //assign values
        mybox.width = 10;
        mybox.height = 20;
        mybox.depth = 15;
        //compute volume
        vol = mybox.width * mybox.height * mybox.depth;
        System.out.println("Volume is " + vol);
        }
}