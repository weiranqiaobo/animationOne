package com.hencoder.hencoderpracticedraw6.practice.practice08;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import static com.hencoder.hencoderpracticedraw6.Utils.dpToPixel;

public class Practice08ObjectAnimatorView extends View {
    final float radius = dpToPixel(80);

    RectF arcRectF = new RectF();
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    // TODO 为 progress 添加 getter 和 setter 方法（setter 方法记得加 invalidate()）
    float progress = 0;

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    public Practice08ObjectAnimatorView(Context context) {
        super(context);
    }

    public Practice08ObjectAnimatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice08ObjectAnimatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setTextSize(dpToPixel(40));
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;

        SweepGradient sweepGradient = new SweepGradient(centerX, centerY, Color.WHITE, Color.parseColor("#E91E63"));
        Matrix matrix = new Matrix();
        matrix.setRotate(130, centerX, centerY);
        sweepGradient.setLocalMatrix(matrix);
        paint.setShader(sweepGradient);
//        paint.setColor(Color.argb(255,Integer.valueOf("E9",16),Integer.valueOf("1E",16),Integer.valueOf("6sweepGradient3",16)));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(dpToPixel(15));
        arcRectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        canvas.drawArc(arcRectF, 135, progress * 3.5f, false, paint);

        float pointX= (float) (centerX+radius*Math.cos(Math.toRadians(135+progress*3.5f)));
        float pointY=(float) (centerY+radius*Math.sin(Math.toRadians(135+progress*3.5f)));

        paint.setShader(null);
        if(progress>10){
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(10);
            canvas.drawPoint(pointX,pointY,paint);
        }
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText((int) progress + "%", centerX, centerY - (paint.ascent() + paint.descent()) / 2, paint);
    }
}
