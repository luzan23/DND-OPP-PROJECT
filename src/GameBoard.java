public class GameBoard
{
    private Cell[][] gameBoard;

    public Occupant getOccupant(Position p){
        return this.gameBoard[p.getX()][p.getY()].getOccupant();
    }

    public Cell getCell(Position p){
        return this.gameBoard[p.getX()][p.getY()];
    }

    public void setOccupant(Position p, Occupant o){
        this.gameBoard[p.getX()][p.getY()].setOccupant(o);
    }

    public void moveUnit(Unit u, Position targetPos){
        Position oldPos= u.getPosition();
        Cell targetCell = this.gameBoard[targetPos.getX()][targetPos.getY()];
        targetCell.accept(u);

        if(!u.getPosition().equals(oldPos)){
            this.gameBoard[oldPos.getX()][oldPos.getY()].setOccupant(null);
        }
    }

}
