package uk.co.coldasice.projects.android.arkadroid.sprites;

import uk.co.coldasice.projects.android.arkadroid.controllers.GameRenderer;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public abstract class Sprite {

	protected final Drawable drawable;
	protected double y;
	protected double x;
	protected double w;
	protected double h;
	private GameRenderer renderer;
	Rect rect = new Rect();
	private boolean killed = false;
	
	public Sprite(Drawable drawable, GameRenderer renderer) {
		this.drawable = drawable;
		this.w = drawable.getIntrinsicWidth();
		this.h = drawable.getIntrinsicHeight();
		this.x = 0;
		this.y = 0;
		this.renderer = renderer;
	}
	
	public double getW() {
		return w;
	}

	public double getH() {
		return h;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void draw (Canvas canv) {
		if (this.killed) return;
		preDraw(canv);
		this.drawable.setBounds((int)x, (int)y, (int)(x+w), (int)(y+h));
		this.drawable.setAlpha(255);
		this.drawable.draw(canv);
		postDraw(canv);
	}
	
	protected abstract void preDraw(Canvas canv);
	protected abstract void postDraw(Canvas canv);
	
	public void setX(double x) {
		if (x < 0) this.x = 0;
		else if (x + w > renderer.getW()) this.setXEdge(this.renderer.getW()-1);
		else this.x = x;
	}
	
	public void setY (double y) {
		if (y < 0) this.y = 0;
		else if (y + h > this.renderer.getH()) this.setYEdge(this.renderer.getH()-1);
		else this.y = y;
	}

	public void setXEdge(double x) {
		this.setX(x - w);
	}
	
	public void setYEdge(double y) {
		this.setY(y - h);
	}
	
	public void setXMiddle(double newX) {
		this.x = newX - (w / 2);
	}
	
	public void setYMiddle(double newY) {
		this.y = newY - (h / 2);
	}
	
	public void setXY (double x, double y) {
		this.setX(x);
		this.setY(y);
	}
	
	public void setXYEdge(double x, double y) {
		this.setXEdge(x);
		this.setYEdge(y);
	}
	
	public void setXYMiddle (double x, double y) {
		this.setXMiddle(x);
		this.setYMiddle(y);
	}
	
	public boolean collidesWith(Sprite sp) {
		return getBounds().intersect(sp.getBounds());
	}

	Rect getBounds() {
		rect.set((int)x, (int)y, (int)(x+w), (int)(y+h));
		return rect;
	}
	
	public void kill() {
		killed = true;
	}
	
	public void unkill() {
		killed  = false;
	}
	
	public boolean dead() {
		return killed;
	}

	public double getMidX() {
		return x + (w/2);
	}
	
}
