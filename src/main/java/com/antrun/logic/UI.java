package com.antrun.logic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class UI {

	private JFrame frame;
	private JTextField route;
	private JTextField allRoute;
	private JTextField loop;
	private JTextField pheromone;
	private JTextField alpha;
	private JTextField ant;
	private JTextField volatileRate;
	private JTextField beta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();

					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public UI() throws Exception{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws Exception{
		frame = new JFrame();
		frame.setFont(new Font("Cordia New", Font.PLAIN, 14));
		frame.setBounds(50, 100, 666, 458);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setForeground(new Color(60, 179, 113));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnSubmit = new JButton("Calculate");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					final int numberOfRoute = Integer.valueOf(route.getText());
					final int numberOfLoop = Integer.valueOf(loop.getText());
					final int numberOfAnt = Integer.valueOf(ant.getText());
					final double initPheromones = Double.valueOf(pheromone.getText());
					final double volatileRateVal = Double.valueOf(volatileRate.getText());
					final double alphaVal = Double.valueOf(alpha.getText());
					final double betaVal = Double.valueOf(beta.getText());

					//READ FILE

					//TODO: Get from Excel file
					final double[][] distanceArray = {
							{0, 10, 15, 8, 4},
							{10, 0, 12, 5, 8},
							{15, 12, 0, 14, 20},
							{8, 5, 14, 0, 15},
							{4, 8, 20, 15, 0}
					};

					//END READ FILE

					HashMap<String, Integer> pp = new HashMap<String, Integer>();

					PheromonesCalculate p = new PheromonesCalculate();
					double[][] pheromonesList = p.pheromonesPowerAlpha(p.initPheromones(numberOfRoute, initPheromones), alphaVal);

					double[][] wIJ = WijCalculation.getWij(pheromonesList, alphaVal, distanceArray, betaVal);

					PreromonesExporter preromonesExporter = new PreromonesExporter(System.getProperty("user.dir") + "/AntRunResult.xls");

					preromonesExporter.printStrings(PreromonesExporter.SHEET_PATH, "Ant No.", "Round", "Route", "Distance");

					for (int l = 0; l < numberOfLoop; l++) {
						preromonesExporter.printString(PreromonesExporter.SHEET_PHERO, "==========preromones list round[" + (l + 1) + "]==========");
						preromonesExporter.printArray(PreromonesExporter.SHEET_PHERO, pheromonesList);
						preromonesExporter.printLint(PreromonesExporter.SHEET_PHERO);

						preromonesExporter.printString(PreromonesExporter.SHEET_WIJ, "==========Wij list round[" + (l + 1) + "]==========");
						preromonesExporter.printArray(PreromonesExporter.SHEET_WIJ, wIJ);
						preromonesExporter.printLint(PreromonesExporter.SHEET_WIJ);

						//System.out.println(Arrays.deepToString(pheromonesList));

						Calculate c = new Calculate();
						//TODO : cal wIJ;

						List<Integer> bestAntRunedTownPath = new ArrayList<>();
						double bestPath = Double.MAX_VALUE;
						for (int a = 0; a < numberOfAnt; a++) {
							List<Integer> antRunedTownPath = new ArrayList<>();
							int town = c.getTown(numberOfRoute);
							antRunedTownPath.add(town);
							do {
								town = c.getTown(wIJ[town], antRunedTownPath);
								//TODO : cal wIJ
								antRunedTownPath.add(town);

							} while (antRunedTownPath.size() < numberOfRoute);
							double currDistance = p.runDistance(distanceArray, antRunedTownPath);
							if (currDistance < bestPath) {
								bestPath = currDistance;
								bestAntRunedTownPath = antRunedTownPath;
							}
							String hu_antNo = String.valueOf(a + 1);
							String hu_roundNo = String.valueOf(l + 1);
							preromonesExporter.printStrings(PreromonesExporter.SHEET_PATH, hu_antNo, hu_roundNo, p.runPath(antRunedTownPath), String.valueOf(p.runDistance(distanceArray, antRunedTownPath)));
							//preromonesExporter.printLint(PreromonesExporter.SHEET_PATH );
						}


						//TODO: Make sure update Pheromones and wIJ only one time
						pheromonesList = p.pheromonesVolatileCalculate(pheromonesList, volatileRateVal);
						pheromonesList = p.pheromonesUpdateCalculate(pheromonesList, bestAntRunedTownPath, p.runDistance(distanceArray, bestAntRunedTownPath));
						//update wIJ
						wIJ = WijCalculation.getWij(p.pheromonesPowerAlpha(pheromonesList, alphaVal), alphaVal, distanceArray, betaVal);
					}
					preromonesExporter.dowrite();

					JOptionPane.showMessageDialog(null, "Calculate Completed");
				}catch (Exception ex){
					JOptionPane.showMessageDialog(null, "Err : "+ex.toString());
					ex.printStackTrace();
				}


			}
		});
		btnSubmit.setForeground(Color.BLUE);
		btnSubmit.setBounds(483, 354, 110, 37);
		panel.add(btnSubmit);

		route = new JTextField();
		route.setHorizontalAlignment(SwingConstants.LEFT);
		route.setBounds(37, 83, 257, 45);
		panel.add(route);
		route.setColumns(10);
		route.setText("5");

		JLabel lblUploadFile = new JLabel(
				"\u0E08\u0E33\u0E19\u0E27\u0E19\u0E40\u0E2A\u0E49\u0E19\u0E17\u0E32\u0E07 :");
		lblUploadFile.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblUploadFile.setHorizontalAlignment(SwingConstants.LEFT);
		lblUploadFile.setLabelFor(lblUploadFile);
		lblUploadFile.setBounds(37, 58, 257, 26);
		panel.add(lblUploadFile);

		JLabel lblCriteria = new JLabel("Criteria");
		lblCriteria.setHorizontalAlignment(SwingConstants.LEFT);
		lblCriteria.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCriteria.setBounds(37, 26, 110, 26);
		panel.add(lblCriteria);

		allRoute = new JTextField();
		allRoute.setFont(new Font("Tahoma", Font.PLAIN, 11));
		allRoute.setHorizontalAlignment(SwingConstants.LEFT);
		allRoute.setColumns(10);
		allRoute.setBounds(336, 83, 257, 45);
		panel.add(allRoute);

		JLabel lblTest = new JLabel(
				"\u0E40\u0E2A\u0E49\u0E19\u0E17\u0E32\u0E07\u0E17\u0E31\u0E49\u0E07\u0E2B\u0E21\u0E14 :");
		lblTest.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblTest.setLabelFor(allRoute);
		lblTest.setHorizontalAlignment(SwingConstants.LEFT);
		lblTest.setBounds(336, 58, 257, 26);
		panel.add(lblTest);

		JLabel label = new JLabel(
				"\u0E08\u0E33\u0E19\u0E27\u0E19\u0E23\u0E2D\u0E1A\u0E43\u0E19\u0E01\u0E32\u0E23\u0E27\u0E19\u0E0B\u0E49\u0E33 :");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		label.setBounds(37, 127, 257, 26);
		panel.add(label);

		loop = new JTextField();
		loop.setHorizontalAlignment(SwingConstants.LEFT);
		loop.setColumns(10);
		loop.setBounds(37, 152, 257, 45);
		loop.setText("10");
		panel.add(loop);

		JLabel label_1 = new JLabel(
				"\u0E04\u0E48\u0E32\u0E1F\u0E35\u0E42\u0E23\u0E42\u0E21\u0E19\u0E40\u0E23\u0E34\u0E48\u0E21\u0E15\u0E49\u0E19 :");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		label_1.setBounds(37, 196, 257, 26);
		panel.add(label_1);

		pheromone = new JTextField();
		pheromone.setHorizontalAlignment(SwingConstants.LEFT);
		pheromone.setColumns(10);
		pheromone.setBounds(37, 221, 257, 45);
		pheromone.setText("1");
		panel.add(pheromone);

		JLabel lblAlpha = new JLabel("ALPHA :");
		lblAlpha.setHorizontalAlignment(SwingConstants.LEFT);
		lblAlpha.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblAlpha.setBounds(37, 265, 257, 26);
		panel.add(lblAlpha);

		alpha = new JTextField();
		alpha.setHorizontalAlignment(SwingConstants.LEFT);
		alpha.setColumns(10);
		alpha.setBounds(37, 290, 257, 45);
		alpha.setText("5");
		panel.add(alpha);

		JLabel label_3 = new JLabel(
				"\u0E08\u0E33\u0E19\u0E27\u0E19\u0E21\u0E14 :");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		label_3.setBounds(336, 127, 257, 26);
		panel.add(label_3);

		ant = new JTextField();
		ant.setHorizontalAlignment(SwingConstants.LEFT);
		ant.setColumns(10);
		ant.setBounds(336, 152, 257, 45);
		ant.setText("10");
		panel.add(ant);

		JLabel label_4 = new JLabel(
				"\u0E2D\u0E31\u0E15\u0E23\u0E32\u0E01\u0E32\u0E23\u0E23\u0E30\u0E40\u0E2B\u0E22 :");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		label_4.setBounds(336, 196, 257, 26);
		panel.add(label_4);

		volatileRate = new JTextField();
		volatileRate.setHorizontalAlignment(SwingConstants.LEFT);
		volatileRate.setColumns(10);
		volatileRate.setBounds(336, 221, 257, 45);
		volatileRate.setText("0.02");
		panel.add(volatileRate);

		JLabel lblBeta = new JLabel("BETA :");
		lblBeta.setHorizontalAlignment(SwingConstants.LEFT);
		lblBeta.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblBeta.setBounds(336, 265, 257, 26);
		panel.add(lblBeta);

		beta = new JTextField();
		beta.setHorizontalAlignment(SwingConstants.LEFT);
		beta.setColumns(10);
		beta.setBounds(336, 290, 257, 45);
		beta.setText("5");
		panel.add(beta);

	}
}
