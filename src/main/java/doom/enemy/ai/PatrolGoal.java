package doom.enemy.ai;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.EntityCreature;
import net.minestom.server.entity.ai.GoalSelector;
import net.minestom.server.instance.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class PatrolGoal extends GoalSelector {
    private final Pos patrolPoint;
    private boolean goingAway = false;
    private Pos targetPoint;

    public PatrolGoal(@NotNull EntityCreature entityCreature, @NotNull Pos patrolPoint) {
        super(entityCreature);

        this.patrolPoint = patrolPoint.add(new Vec(0.5, 0, 0.5));
    }
    @Override
    public boolean shouldStart() {
        return true;
    }

    public boolean reachedTarget() {
        return entityCreature.getPosition().sameBlock(targetPoint);
    }
    public Pos randomPatrolPoint(int distance) {
        Random rand = new Random();
        int randVal = rand.nextInt(distance * 2) + 1; // range = [1,(distance * 2)]
        int dx = randVal > distance ? -(randVal - distance) : randVal; // can be -1 to -distance, or 1 to distance (inclusive both sides)
        randVal = rand.nextInt(distance * 2) + 1;
//        int dy = randVal > distance ? -(randVal - distance) : randVal;
//        randVal = rand.nextInt(distance * 2) + 1;
        int dz = randVal > distance ? -(randVal - distance) : randVal;
        return patrolPoint.add(new Pos(dx + 0.5, 0, dz + 0.5));
    }
    @Override
    public void start() {
        System.out.println("Starting patrolling around " + patrolPoint);
        System.out.println("entity is currently at " + entityCreature.getPosition());
        targetPoint = patrolPoint;
    }

    @Override
    public void tick(long time) {
        boolean atTarget = reachedTarget();
        if (atTarget) {
            if (targetPoint.sameBlock(patrolPoint)) {
                Pos newTarget = randomPatrolPoint(2);
                targetPoint = newTarget;
            }
            else {
                targetPoint = patrolPoint;
            }
            entityCreature.getNavigator().setPathTo(null, true);
            var isValid = entityCreature.getNavigator().setPathTo(targetPoint, true);
            if (!isValid){
            }
            else {
            }
        }
    }

    @Override
    public boolean shouldEnd() {
        if (entityCreature.isDead()) {
            System.out.println("entity is dead, ending");
        }
        return entityCreature.isDead();
    }

    @Override
    public void end() {
        System.out.println("Patrolling creature died!");
    }
}
