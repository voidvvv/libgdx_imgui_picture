package com.voidvvv.imgui.test.entity.frame;

import com.badlogic.gdx.math.Rectangle;


public class AttackCheck {
    public final Rectangle rectangle = new Rectangle();
    final String damageType;


    float absoluteDamage;

    public AttackCheck(Rectangle other , String damageType, float absoluteDamage) {
        this.rectangle.set(other);
        this.damageType = damageType;
        this.absoluteDamage = absoluteDamage;
    }

}
