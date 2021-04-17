public class PhysicsObject {
    // Sample PhysicsObjects
    public static PhysicsObject air = new PhysicsObject(' ', false, 0, 0);
    public static PhysicsObject sand = new PhysicsObject('s', true, 0, 0);
    public static PhysicsObject rock  = new PhysicsObject('r', false, 0, 0);

    private char disp;
    private boolean hasGravity;
    /*
    private boolean bounces;
    private int x;
    private int y;
    */
    private int dx;
    private int dy;
    
    public PhysicsObject(char disp, boolean hasGravity, /*, boolean bounces, int x, int y,*/ int dx, int dy){
        this.disp = disp;
        this.hasGravity = hasGravity;
        this.dx = dx;
        this.dy = dy;
        /*
        this.bounces = bounces;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        */
    }

    public PhysicsObject copy(){
        PhysicsObject output = new PhysicsObject(this.disp, this.hasGravity, this.dx, this.dy);
        return output;
    }
    
    public char getDisp(){return this.disp;}

    public boolean getHasGravity(){return this.hasGravity;}

    public void setHasGravity(boolean hasGravity){this.hasGravity = hasGravity;}

    public int getDy(){return this.dy;}

    public void setDy(int dy){this.dy = dy;}

    public int getDx(){return this.dx;}

    public void setDx(int dx){this.dx = dx;}
}
