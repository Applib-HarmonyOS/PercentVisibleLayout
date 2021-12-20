package com.example.percentvisiblelayout.slice;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import com.example.percentvisiblelayout.ResourceTable;



/**
 * MainAbilitySlice.
 */
public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);



        findComponentById(ResourceTable.Id_btn_horizontal).setClickedListener(component -> {
            Intent horizontalIntent = new Intent();
            Operation operation = new Intent.OperationBuilder().withDeviceId("")
                    .withBundleName("com.example.percentvisiblelayout")
                    .withAbilityName("com.example.percentvisiblelayout.views.Horizontal")
                    .build();
            horizontalIntent.setOperation(operation);
            startAbility(horizontalIntent);
        });


        findComponentById(ResourceTable.Id_btn_vertical).setClickedListener(component -> {
            Intent verticalIntent = new Intent();
            Operation operation = new Intent.OperationBuilder().withDeviceId("")
                    .withBundleName("com.example.percentvisiblelayout")
                    .withAbilityName("com.example.percentvisiblelayout.views.Vertical")
                    .build();
            verticalIntent.setOperation(operation);
            startAbility(verticalIntent);
        });
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

}
