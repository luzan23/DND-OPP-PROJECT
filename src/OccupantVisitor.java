public interface OccupantVisitor {
    void visit(Player p);
    void visit(Enemy e);
}
