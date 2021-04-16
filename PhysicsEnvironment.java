public class PhysicsEnvironment{
    private int x;
    private int y;
    private PhysicsObject[][] board;
    private boolean[][] iterated;

    public PhysicsEnvironment(int x, int y){ // Constructor (width, height)
        this.x = x;
        this.y = y;
        this.iterated = new boolean[y][x];
        board = new PhysicsObject[y][x];
        this.instantiate();
    }

    public void instantiate(){ // Fills the given PhysicsEnvironment with "air" PhysicsObjects ('', no gravity)
        this.fillAllIndexes(PhysicsObject.air);
    }
    public void iterate(){ // Iterates the given PhysicsEnvironment's physics by 1 frame
        this.iterated = new boolean[this.y][this.x];
        boolean changed = false;
        for(int i = 0; i < this.board.length; i++){ // test
            changed = false;
            for(int j = 0; j < this.board[0].length; j++){
                if(this.board[i][j].getHasGravity() && i < this.board.length - 1 && !this.iterated[i][j] && this.board[i + 1][j].getDisp() == ' '){
                    this.board[i + 1][j] = this.board[i][j];
                    this.board[i][j] = PhysicsObject.air;
                    this.iterated[i + 1][j] = true;
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
            try{Thread.sleep(300);}catch(Exception e){System.out.println("An Error occurred. (Thread.sleep)");}
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
        pe.placeObject(40, pe.board.length - 10, air);
        pe.iterate(20);
        // after school test
    }
}