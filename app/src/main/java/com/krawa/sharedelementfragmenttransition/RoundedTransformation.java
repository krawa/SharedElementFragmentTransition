/**
 *
 */
package com.krawa.sharedelementfragmenttransition;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

// enables hardware accelerated rounded corners
// original idea here : http://www.curious-creature.org/2012/12/11/android-recipe-1-image-with-rounded-corners/
public class RoundedTransformation implements com.squareup.picasso.Transformation {
	private final int radius;
	private final int margin; // dp
	private int marginColor;
 
// radius is corner radii in dp
// margin is the board in dp
	public RoundedTransformation(final int radius, final int margin) {
		this(radius, margin, 0);
	}
	
	public RoundedTransformation(final int radius, final int margin, int marginColor) {
		this.radius = radius;
		this.margin = margin;
		this.marginColor = marginColor;
	}
 
	@Override
	public Bitmap transform(final Bitmap source) {
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));

		Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		
		if(marginColor != 0){
			Paint marginPaint = new Paint();
			marginPaint.setAntiAlias(true);
			marginPaint.setColor(marginColor);
			canvas.drawRoundRect(new RectF(0, 0, source.getWidth(), source.getHeight()), radius, radius, marginPaint);
		}

		canvas.drawRoundRect(new RectF(margin, margin, source.getWidth() - margin, source.getHeight() - margin), radius, radius, paint);
 
		if (source != output) {
			source.recycle();
		}
 
		return output;
	}
 
	@Override
	public String key() {
		return "rounded(radius=" + radius + ", margin=" + margin + ")";
	}
}
