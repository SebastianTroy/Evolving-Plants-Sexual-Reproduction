package EvolvingPlants;

import java.awt.Color;

import TroysCode.Tools;
import TroysCode.hub;

public class Genes
	{
		protected String DNA = "";
		private final int DNA_LENGTH = 30;
		private final int DNA_MUTABILITY = 10;
		private final char A = 0;
		private final char B = 1;
		private final char C = 2;

		public static final int SPECIES_VAR = 5;

		private final int MINPERCENT = 0;
		private final int MAXPERCENT = 100;

		public Color leafColour;
		public Color seedColour;

		public int maxAge;
		private final int AGE_VAR = 300;

		public float seedEnergy;
		private final float SEED_ENERGY_VAR = 30;
		public float seedSpread;
		private final float SEED_SPREAD_VAR = 0.25f;
		public float numberOfSeedStems;
		private final float NUM_SEED_STEM_VAR = 3;

		public float maxStems;
		private final float MAX_STEM_VAR = 2;
		public float numberOfLeafStems;
		private final float NUM_LEAF_STEMS_VAR = 2;
		public float chanceOfGrowingStems;
		private final float CHANCE_LEAF_HAS_STEMS_VAR = 10;
		public float maxStemLength;
		private final float MAX_STEM_LENGTH_VAR = 8;
		public float stemAngleVariation;
		private final float stamAngleVar = 15;

		public double energyTransfer;
		private final double stemGrowSpeedVar = 0.08;

		protected boolean germinate = true;

		public Genes(Plant parentOne, Plant parentTwo, Plant thisPlant)
			{
				// 50:50 % chance of genes being interpolated, or simply
				// randomly chosen from a single parent
				if (parentOne != null && parentTwo != null && Tools.randBool())
					{
						mergeParentsDNA(parentOne.genes.DNA, parentTwo.genes.DNA);

						leafColour = Tools.interpolateColours(parentOne.genes.leafColour, parentOne.genes.leafColour);
						seedColour = Tools.interpolateColours(parentOne.genes.seedColour, parentOne.genes.seedColour);

						maxAge = (parentOne.genes.maxAge + parentTwo.genes.maxAge) / 2;

						seedEnergy = (parentOne.genes.seedEnergy + parentTwo.genes.seedEnergy) / 2;
						seedSpread = (parentOne.genes.seedSpread + parentTwo.genes.seedSpread) / 2;

						numberOfSeedStems = (parentOne.genes.numberOfSeedStems + parentTwo.genes.numberOfSeedStems) / 2;
						numberOfLeafStems = (parentOne.genes.numberOfLeafStems + parentTwo.genes.numberOfLeafStems) / 2;

						maxStems = (parentOne.genes.maxStems + parentTwo.genes.maxStems) / 2;
						chanceOfGrowingStems = (parentOne.genes.chanceOfGrowingStems + parentTwo.genes.chanceOfGrowingStems) / 2;
						maxStemLength = (parentOne.genes.maxStemLength + parentTwo.genes.maxStemLength) / 2;
						stemAngleVariation = (parentOne.genes.stemAngleVariation + parentTwo.genes.stemAngleVariation) / 2;

						energyTransfer = (parentOne.genes.energyTransfer + parentTwo.genes.energyTransfer) / 2;
					}
				else if (parentOne != null && parentTwo != null)
					{
						DNA = Tools.randBool() ? parentOne.genes.DNA : parentTwo.genes.DNA;

						leafColour = Tools.interpolateColours(parentOne.genes.leafColour, parentOne.genes.leafColour);
						seedColour = Tools.randBool() ? parentOne.genes.seedColour : parentTwo.genes.seedColour;

						maxAge = Tools.randBool() ? parentOne.genes.maxAge : parentTwo.genes.maxAge;

						seedEnergy = Tools.randBool() ? parentOne.genes.seedEnergy : parentTwo.genes.seedEnergy;
						seedSpread = Tools.randBool() ? parentOne.genes.seedSpread : parentTwo.genes.seedSpread;

						numberOfSeedStems = Tools.randBool() ? parentOne.genes.numberOfSeedStems : parentTwo.genes.numberOfSeedStems;
						numberOfLeafStems = Tools.randBool() ? parentOne.genes.numberOfLeafStems : parentTwo.genes.numberOfLeafStems;

						maxStems = Tools.randBool() ? parentOne.genes.maxStems : parentTwo.genes.maxStems;
						chanceOfGrowingStems = Tools.randBool() ? parentOne.genes.chanceOfGrowingStems : parentTwo.genes.chanceOfGrowingStems;
						maxStemLength = Tools.randBool() ? parentOne.genes.maxStemLength : parentTwo.genes.maxStemLength;
						stemAngleVariation = Tools.randBool() ? parentOne.genes.stemAngleVariation : parentTwo.genes.stemAngleVariation;

						energyTransfer = Tools.randBool() ? parentOne.genes.energyTransfer : parentTwo.genes.energyTransfer;
					}
				else
					newDNA();

				mutate(thisPlant);
				checkGenes();
			}

		protected final void newDNA()
			{
				for (int i = 0; i < DNA_LENGTH; i++)
					{
						char letter = (char) Tools.randInt(0, 2);
						switch (letter)
							{
							case (A):
								DNA += A;
								break;
							case (B):
								DNA += B;
								break;
							case (C):
								DNA += C;
								break;
							}
					}

				leafColour = Tools.randAlphaColour();
				seedColour = Tools.randColour();

				maxAge = Tools.randInt(800, 2800);

				seedEnergy = Tools.randInt(250, 350);
				seedSpread = Tools.randFloat(1.0f, 1.7f);
				numberOfSeedStems = Tools.randFloat(1.0f, 2.0f);

				maxStems = Tools.randFloat(1, 4);
				numberOfLeafStems = Tools.randFloat(0, 3);
				chanceOfGrowingStems = (float) Tools.randPercent();
				maxStemLength = Tools.randInt(25, 55);
				stemAngleVariation = Tools.randFloat(0, 30);

				energyTransfer = Tools.randDouble(0.01, 3.0);
			}

		protected final void mergeParentsDNA(String pOne, String pTwo)
			{
				for (int i = 0; i < DNA_LENGTH; i++)
					{
						char letter = Tools.randBool() ? pOne.charAt(i) : pTwo.charAt(i);

						switch (letter)
							{
							case (A):
								DNA += A;
								break;
							case (B):
								DNA += B;
								break;
							case (C):
								DNA += C;
								break;
							}
					}
			}

		protected final void mutateDNA(double var)
			{
				String NEW_DNA = "";

				for (int i = 0; i < DNA_LENGTH; i++)
					{
						if (Tools.randPercent() < Tools.randDouble(0, var * DNA_MUTABILITY))
							{
								char letter = (char) Tools.randInt(0, 2);

								switch (letter)
									{
									case (A):
										NEW_DNA += A;
										break;
									case (B):
										NEW_DNA += B;
										break;
									case (C):
										NEW_DNA += C;
										break;
									}
							}
						else
							NEW_DNA += DNA.charAt(i);
					}
				DNA = NEW_DNA;
			}

		protected final boolean isSameSpecies(Plant plant)
			{
				int numDifferences = 0;

				for (int i = 0; i < DNA_LENGTH; i++)
					if (DNA.charAt(i) != plant.genes.DNA.charAt(i))
						numDifferences++;

				return numDifferences < SPECIES_VAR;
			}

		protected final int getSpeciesDifference(Plant plant)
			{
				int numDifferences = 0;

				for (int i = 0; i < DNA_LENGTH; i++)
					if (DNA.charAt(i) != plant.genes.DNA.charAt(i))
						numDifferences++;

				return numDifferences;
			}

		protected final boolean isGeneticallyCompatable(Plant plant)
			{
				int numDifferences = 0;

				for (int i = 0; i < DNA_LENGTH; i++)
					if (DNA.charAt(i) != plant.genes.DNA.charAt(i))
						numDifferences++;

				// the more closely related, the more likely they are
				// compatable.
				return numDifferences < SPECIES_VAR - Tools.randInt(0, SPECIES_VAR);
			}

		private final void mutate(Plant thisPlant)
			{
				float UVIntensity = 0f;
				float var = 0f;

				// can be null if plant doesn't exist in the world
				if (thisPlant != null)
					{
						UVIntensity = thisPlant.x < 600 ? hub.world.UVIntensity : hub.world.UVIntensity2;
						var = thisPlant.x < 600 ? hub.world.UVDamage : hub.world.UVDamage2;
					}

				if (UVIntensity > Tools.randPercent())
					{
						var /= 100f;

						mutateColour(thisPlant);

						mutateDNA(var);

						maxAge += Tools.randFloat(-var * AGE_VAR, var * AGE_VAR);

						seedEnergy += Tools.randFloat(-var * SEED_ENERGY_VAR, var * SEED_ENERGY_VAR);
						seedSpread += Tools.randFloat(-var * SEED_SPREAD_VAR, var * SEED_SPREAD_VAR);

						numberOfSeedStems += Tools.randFloat(-var * NUM_SEED_STEM_VAR, var * NUM_SEED_STEM_VAR);
						numberOfLeafStems += Tools.randFloat(-var * NUM_LEAF_STEMS_VAR, var * NUM_LEAF_STEMS_VAR);

						maxStems += Tools.randFloat(-var * MAX_STEM_VAR, var * MAX_STEM_VAR);
						chanceOfGrowingStems += Tools.randFloat(-var * CHANCE_LEAF_HAS_STEMS_VAR, var * CHANCE_LEAF_HAS_STEMS_VAR);
						maxStemLength += Tools.randFloat(-var * MAX_STEM_LENGTH_VAR, var * MAX_STEM_LENGTH_VAR);
						stemAngleVariation += Tools.randFloat(-var * stamAngleVar, var * stamAngleVar);

						energyTransfer += Tools.randDouble(-var * stemGrowSpeedVar, var * stemGrowSpeedVar);
					}
			}

		private final void checkGenes()
			{
				if (numberOfSeedStems < 1 || maxStems < 1 || seedEnergy < 5 || energyTransfer <= 0)
					germinate = false;

				if (numberOfLeafStems < 0)
					numberOfLeafStems = 0;

				if (stemAngleVariation < 0)
					stemAngleVariation = 0;

				if (chanceOfGrowingStems < MINPERCENT)
					chanceOfGrowingStems = MINPERCENT;

				if (chanceOfGrowingStems > MAXPERCENT)
					chanceOfGrowingStems = MAXPERCENT;
			}

		private final void mutateColour(Plant thisPlant)
			{
				float var = 5f;

				if (thisPlant != null)
					var = thisPlant.x < 600 ? hub.world.UVDamage / 1.5f : hub.world.UVDamage2 / 1.5f;

				int alpha = (int) (leafColour.getAlpha() + Tools.randFloat(-var, var));
				int red = (int) (leafColour.getRed() + Tools.randFloat(-var, var));
				int green = (int) (leafColour.getGreen() + Tools.randFloat(-var, var));
				int blue = (int) (leafColour.getBlue() + Tools.randFloat(-var, var));

				leafColour = Tools.checkAlphaColour(red, green, blue, alpha);

				var *= 2.5;

				red = (int) (seedColour.getRed() + Tools.randFloat(-var, var));
				green = (int) (seedColour.getGreen() + Tools.randFloat(-var, var));
				blue = (int) (seedColour.getBlue() + Tools.randFloat(-var, var));

				seedColour = Tools.checkColour(red, green, blue);
			}
	}
