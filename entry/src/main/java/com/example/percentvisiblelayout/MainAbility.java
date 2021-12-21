package com.example.percentvisiblelayout;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import com.example.percentvisiblelayout.slice.MainAbilitySlice;


/**
 * MainAbility.
 */
public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
    }
}
