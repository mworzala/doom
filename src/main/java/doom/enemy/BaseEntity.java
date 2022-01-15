package doom.enemy;

import doom.enemy.ai.PatrolGoal;
import net.minestom.server.attribute.Attribute;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.EntityCreature;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.LivingEntity;
import net.minestom.server.entity.ai.EntityAIGroup;
import net.minestom.server.entity.ai.EntityAIGroupBuilder;
import org.jetbrains.annotations.NotNull;

public class BaseEntity extends EntityCreature {
    private Pos patrolPos;
//    private Pos secondPatrolPos;
//    private boolean goingTowardsPatrolPos = false;
    public BaseEntity(Pos patrolPos) {
        super(EntityType.ZOMBIE);
        this.patrolPos = patrolPos;

        this.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.1f);
        addAIGroup(new EntityAIGroupBuilder()
                .addGoalSelector(new PatrolGoal(this, patrolPos))
                .build());
    }


//    @Override
//    public void update(long time) {
////        if (goingTowardsPatrolPos) {
////
////        }
////        else {
////
////        }
//    }
}
