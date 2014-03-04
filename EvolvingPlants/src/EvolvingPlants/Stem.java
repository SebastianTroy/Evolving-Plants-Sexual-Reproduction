package EvolvingPlants;

import java.awt.Color;
import java.awt.Graphics;

import tools.NumTools;
import tools.RandTools;

public class Stem extends PlantPart
	{
		private Leaf leaf;

		private float tipX;
		private float tipY;

		private double growX;
		private double growY;

		private double energyPerGrow;

		public Stem(Plant thisPlant, float x, float y)
			{
				super(thisPlant, x, y);
				tipX = x;
				tipY = y;

				energyPerGrow = thisPlant.genes.energyTransfer * Math.pow(thisPlant.genes.energyTransfer + 0.2, 1.25);
				calculateGrowthPath();

				leaf = new Leaf(thisPlant, tipX, tipY);
			}

		private final void calculateGrowthPath()
			{
				float stemAngleVar = thisPlant.genes.stemAngleVariation;
				double growAngle = RandTools.getFloat(-stemAngleVar, stemAngleVar);

				double[] growVector = NumTools.getVector(growAngle);

				growX = growVector[0] * thisPlant.genes.energyTransfer;
				growY = growVector[1] * thisPlant.genes.energyTransfer;
			}

		private final int totatStemLength()
			{
				return (int) NumTools.distance(x, y, tipX, tipY);
			}

		private final void grow()
			{
				tipY -= growY;
				tipX += growX;
				leaf.move(growX, growY);
				energy -= energyPerGrow;
			}

		protected final void move(double xMod, double yMod)
			{
				tipX += xMod;
				tipY -= yMod;
				x += xMod;
				y -= yMod;
				leaf.move(xMod, yMod);
			}

		@Override
		public final void tick()
			{
				if (totatStemLength() < thisPlant.genes.maxStemLength)
					{
						Main.world.lightUpdateNeeded = true;

						if (energy > energyPerGrow)
							grow();

						else if (leaf.energy > energyPerGrow)
							{
								leaf.energy -= energyPerGrow;
								energy += energyPerGrow;
								grow();
							}
					}
				else if (energy > thisPlant.genes.energyTransfer)
					{
						leaf.energy += thisPlant.genes.energyTransfer;
						energy -= thisPlant.genes.energyTransfer;
					}

				leaf.tick();
			}

		@Override
		public final void render(Graphics g)
			{
				g.setColor(thisPlant.selected ? Color.WHITE : Color.BLACK);
				g.drawLine(Math.round(x), Math.round(y), Math.round(tipX), Math.round(tipY));

				leaf.render(g);
			}
	}
