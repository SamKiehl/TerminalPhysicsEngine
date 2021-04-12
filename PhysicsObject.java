public class PhysicsObject {
    public static PhysicsObject air = new PhysicsObject(' ', false);
    public static PhysicsObject sand = new PhysicsObject('s', true);
    public static PhysicsObject rock  = new PhysicsObject('r', false);
    private char disp;
    private boolean hasGravity;
    /*
    private boolean bounces;
    private int x;
    private int y;
    private int dx;
    private int dy;
    */
    public PhysicsObject(char disp, boolean hasGravity/*, boolean bounces, int x, int y, int dx, int dy*/){
        this.disp = disp;
        this.hasGravity = hasGravity;
        /*
        this.bounces = bounces;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        */
    }
    
    public char getDisp(){
        return this.disp;
    }

    public boolean getHasGravity(){
        return this.hasGravity;
    }
}
