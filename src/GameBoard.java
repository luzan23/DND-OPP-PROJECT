public class GameBoard
{
    private Cell[][] board;

    public Occupant getOccupant(Position p){
        return this.board[p.getX()][p.getY()].getOccupant();
    }

    public Cell getCell(Position p){
        return this.board[p.getX()][p.getY()];
    }

    public void setOccupant(Position p, Occupant o){
        this.board[p.getX()][p.getY()].setOccupant(o);
    }

}
