package Menu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class BevelRoundedCornerButton extends JButton {
	private static final int ARC_WIDTH = 30;
	private static final int ARC_HEIGHT = 30;
	protected static final int FOCUS_STROKE = 2;
	protected Shape shape;
	protected Shape border;
	protected Shape base;

	public BevelRoundedCornerButton(String text) {
		super(text);
	}

	@Override
	public void updateUI() {
		super.updateUI();
		setContentAreaFilled(false);
		setFocusPainted(false);
		setBackground(new Color(250, 250, 250));
		initShape();
	}

	protected void initShape() {
		if (!getBounds().equals(base)) {
			base = getBounds();
			int a = FOCUS_STROKE / 2;
			int w = getWidth() - 1;
			int h = getHeight() - 1;
			shape = new RoundRectangle2D.Float(0, 0, w, h, ARC_WIDTH, ARC_HEIGHT);
			border = new RoundRectangle2D.Float(a, a, w - a, h - a, ARC_WIDTH, ARC_HEIGHT);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		initShape();
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (getModel().isArmed()) {
			g2.setColor(new Color(0, 0, 0));
			g2.fill(shape);
		} else {
			g2.setColor(getBackground());
			g2.fill(shape);
		}
		g2.dispose();
		super.paintComponent(g);
	}

	@Override
	protected void paintBorder(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(2f));

		Rectangle r = border.getBounds();
		r.grow(ARC_WIDTH + FOCUS_STROKE / 2, FOCUS_STROKE / 2);
		Path2D.Double lb = new Path2D.Double();
		lb.moveTo(r.x + r.width, r.y);
		lb.lineTo(r.x + r.width, r.y + r.height);
		lb.lineTo(r.x, r.y + r.height);
		lb.closePath();

		g2.setColor(Color.BLACK);
		g2.setClip(lb);
		g2.draw(border);

		g2.setColor(Color.BLACK);
		Area area = new Area(shape);
		area.subtract(new Area(lb));
		g2.setClip(area);
		g2.draw(border);

		g2.dispose();
	}

	@Override
	public boolean contains(int x, int y) {
		return shape == null ? false : shape.contains(x, y);
	}

}