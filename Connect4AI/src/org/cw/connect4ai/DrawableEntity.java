package org.cw.connect4ai;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;

public class DrawableEntity {
	protected ShapeDrawable drawable;
	protected Point position;
	
	public DrawableEntity(Point position) {
		this.position = position;
	}
	
	public void draw(Canvas canvas) {
		drawable.draw(canvas);
	}
}
