package com.kis.meigen_generator.button;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/*** プッシュするエフェクトのボタン ***/
public class PushButton extends androidx.appcompat.widget.AppCompatButton {
    public PushButton(Context context) {
        super(context);
    }

    public PushButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setPressed(boolean pressed) {
        if(pressed){
            this.setScaleY(0.72f);
            this.setScaleX(0.76f);
        }else{
            this.setScaleY(1.0f);
            this.setScaleX(1.0f);
        }
        super.setPressed(pressed);
    }

}