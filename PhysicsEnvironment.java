public class PhysicsEnvironment{
    private int x;
    private int y;
    private PhysicsObject[][] board;
    private boolean[][] iteratedGrav, iteratedDx, iteratedDy;

    public PhysicsEnvironment(int x, int y){ // Constructor (width, height)
        this.x = x;
        this.y = y;
        this.iteratedGrav = new boolean[y][x];
        this.board = new PhysicsObject[y][x];
        this.instantiate();
    }

    public void instantiate(){ // Fills the given PhysicsEnvironment with "air" PhysicsObjects ('', no gravity)
        this.fillAllIndexes(PhysicsObject.air);
    }
    public void iterate(){ // Iterates the given PhysicsEnvironment's physics by 1 frame
        this.iteratedGrav = new boolean[this.y][this.x];
        this.iteratedDx = new boolean[this.y][this.x];
        boolean changed = false;
        
        for(int i = 0; i < this.board.length; i++){ // test
            changed = false;
            for(int j = 0; j < this.board[0].length; j++){
                if(!this.iteratedGrav[i][j] && this.board[i][j].getHasGravity() && i < this.board.length - 1 && this.board[i + 1][j].getDisp() == ' '){
                    this.move(j, i, j, i + 1);
                    this.iteratedGrav[i + 1][j] = true;
                    changed = true;
                }
                if(!this.iteratedDx[i][j] && this.board[i][j].getDisp() != ' ' && this.board[i][j].getDx() > 0 && j != x - 1 && this.board[i][j + 1].getDisp() == ' '){
                    //move right
                    this.move(j, i, j + 1, i);
                    this.iteratedDx[i][j + 1] = true;
                    //set dx = (dx - 1)
                    this.board[i][j].setDx(0);
                    this.board[i][j + 1].setDx(this.board[i][j + 1].getDx() - 1);
                    changed = true;
                }
            }
            if(changed){
                System.out.println(this);
            }
        }
    }
    public void iterate(int times){ // Iterates the given PhysicsEnvironment's physics 'times' times
        for(int i = 0; i < times; i++){
            this.iterate();
            try{Thread.sleep(200);}catch(Exception e){System.out.println("An Error occurred. (Thread.sleep)");}
        }
    }

    public int getWidth(){ // Returns width of PhysicsEnvironment (x)
        return this.x;
    }

    public int getHeight(){ // Returns Height of PhysicsEnvironment (y)
        return this.y;
    }

    public void fillAllIndexes(PhysicsObject po){
        for(int i = 0; i < this.y; i++){
            for(int j = 0; j < this.x; j++){
                this.board[i][j] = po;
            }
        }
    }

    public void fillRow(int row, PhysicsObject po){
        for(int i = 0; i < this.x; i++){
            this.board[row][i] = po;
        }
    }

    public void placeObject(int col, int row, PhysicsObject po){
        this.board[row][col] = po;
    }

    public void setDx(int col, int row, int dx){
        this.board[row][col].setDx(dx);
    }

    public void setDy(int col, int row, int dy){
        this.board[row][col].setDy(dy);
    }

    public void set(int col, int row, PhysicsObject po){
        this.board[row][col] = po;
    }

    public void move(int col1, int row1, int col2, int row2){
        PhysicsObject temp = this.board[row1][col1].copy();
        this.board[row2][col2] = temp;
        this.board[row1][col1] = PhysicsObject.air;
    }

    public String toString(){
        String output = "";
        for(int i = 0; i < this.y; i++){
            for(int j = 0; j < this.x; j++){
                output += this.board[i][j].getDisp();
            }
            output += "\t" + i + "\n";
        }
        return output;
    }
    
    public static void main(String[] args){
        PhysicsEnvironment pe = new PhysicsEnvironment(188, 42);
        PhysicsObject air = PhysicsObject.air;
        PhysicsObject sand = PhysicsObject.sand;
        PhysicsObject rock = PhysicsObject.rock;
        
        pe.fillRow(pe.board.length - 1, sand);
        pe.fillRow(0, sand);
        pe.fillRow(1, sand);

        pe.placeObject(19, pe.board.length - 2, sand);
        pe.placeObject(20, pe.board.length - 2, sand);
        pe.placeObject(21, pe.board.length - 2, sand);
        pe.placeObject(22, pe.board.length - 2, sand);
        pe.placeObject(23, pe.board.length - 2, sand);
        pe.placeObject(24, pe.board.length - 2, sand);
        pe.placeObject(25, pe.board.length - 2, sand);

        pe.placeObject(20, pe.board.length - 3, sand);
        pe.placeObject(21, pe.board.length - 3, sand);
        pe.placeObject(22, pe.board.length - 3, sand);
        pe.placeObject(23, pe.board.length - 3, sand);
        pe.placeObject(24, pe.board.length - 3, sand);

        pe.placeObject(21, pe.board.length - 4, sand);
        pe.placeObject(22, pe.board.length - 4, sand);
        pe.placeObject(23, pe.board.length - 4, sand);

        pe.placeObject(22, pe.board.length - 5, sand);

        pe.placeObject(40, pe.board.length - 10, rock);

        System.out.println(pe);
        pe.iterate(43);
        //pe.iterate();
        pe.setDx(40, pe.board.length - 11, 10);


        
        //pe.placeObject(40, pe.board.length - 10, air);

        for(int i = 0; i < 10; i++){
            System.out.println(pe.board[35][22].getDx());
            pe.iterate();
        }
        // after school test 
    }
}