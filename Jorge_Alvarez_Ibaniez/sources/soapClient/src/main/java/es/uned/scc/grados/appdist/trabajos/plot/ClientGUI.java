package es.uned.scc.grados.appdist.trabajos.plot;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import es.uned.scc.grados.appdist.trabajos.ws.client.jaxws.generated.SignalData;
import es.uned.scc.grados.appdist.trabajos.ws.client.jaxws.generated.SignalParameters;
import es.uned.scc.grados.appdist.trabajos.ws.client.jaxws.generated.WaveForm;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientGUI implements Runnable {

	private JFrame frame;
	private JTextField amplitudeTextField;
	private JTextField frequencytextField;
	@SuppressWarnings("rawtypes")
	private JComboBox waveFormsComboBox;
	private JButton btnStartButton;
	private JButton btnStopButton;
	JLabel lblMessagejlabel;

	private XYSeries dataSerie;
	private static int NMAX = 200;

	private ClientPlot client = null;
	
	private boolean getData = false;
	private boolean settingParameters = false;
	private Thread thread = null;
	private long delay = 1000;
	private JPanel plotPanel;
	private JSlider delaySlider;
	private JLabel lblDelayLabel;

	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Create the application.
	 */
	public ClientGUI(ClientPlot client) {
		initialize();
		this.client = client;
		updateSignalParameters();
		// Set delay in GUI
		int d = (int)delay;
		this.delaySlider.setValue(d);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				closeFrame();
			}
		});
		frame.setResizable(false);
		// frame.setBounds(100, 100, 608, 471);
		frame.setSize(700, 468);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel signalPanel = new JPanel();
		frame.getContentPane().add(signalPanel, BorderLayout.EAST);
		signalPanel.setLayout(new GridLayout(8, 1, 0, 0));

		JLabel lblWaveForm = new JLabel("Wave Form");
		signalPanel.add(lblWaveForm);

		waveFormsComboBox = new JComboBox();
		waveFormsComboBox.setModel(new DefaultComboBoxModel(WaveForm.values()));
		signalPanel.add(waveFormsComboBox);

		JLabel lblAmplitude = new JLabel("Amplitude");
		signalPanel.add(lblAmplitude);

		amplitudeTextField = new JTextField();
		amplitudeTextField.setText("1.0");
		signalPanel.add(amplitudeTextField);
		amplitudeTextField.setColumns(10);

		JLabel lblFrequency = new JLabel("Frequency");
		signalPanel.add(lblFrequency);

		frequencytextField = new JTextField();
		frequencytextField.setText("1.0");
		signalPanel.add(frequencytextField);
		frequencytextField.setColumns(10);

		JSeparator separator = new JSeparator();
		signalPanel.add(separator);

		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				applySignalParametersChanges();
			}
		});
		signalPanel.add(btnApply);

		JPanel controlPanel = new JPanel();
		frame.getContentPane().add(controlPanel, BorderLayout.SOUTH);

		btnStartButton = new JButton("Start");
		btnStartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startSignalGenerator();
			}
		});
		controlPanel.add(btnStartButton);

		btnStopButton = new JButton("Stop");
		btnStopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopSignalGenerator();
			}
		});
		btnStopButton.setEnabled(false);
		controlPanel.add(btnStopButton);
		
		delaySlider = new JSlider();
		delaySlider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				changeDelay();
			}
		});
		delaySlider.setMajorTickSpacing(100);
		delaySlider.setPaintTicks(true);
		delaySlider.setValue(1000);
		delaySlider.setMaximum(1000);
		delaySlider.setMinimum(10);
		controlPanel.add(delaySlider);
		
		lblDelayLabel = new JLabel("1 second");
		controlPanel.add(lblDelayLabel);

		plotPanel = new JPanel();
		frame.getContentPane().add(plotPanel, BorderLayout.CENTER);
		ChartPanel chart_panel = createPlot();
		plotPanel.add(chart_panel);
		
		
		lblMessagejlabel = new JLabel("Press start...");
		lblMessagejlabel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(lblMessagejlabel, BorderLayout.NORTH);
	}

	public void applySignalParametersChanges() {

		if (client != null) {
			WaveForm type;
			// Check for values
			int index = this.waveFormsComboBox.getSelectedIndex();
			switch (index) {
			case 1:
				type = WaveForm.SQUARE;
				break;
			case 2:
				type = WaveForm.TRIANGLE;
				break;
			default:
				type = WaveForm.SINE;
			}
			double amplitude = Double.parseDouble(this.amplitudeTextField
					.getText());
			double frequency = Double.parseDouble(this.frequencytextField
					.getText());

			SignalParameters sp = new SignalParameters();

			sp.setAmplitude(amplitude);
			sp.setFrequency(frequency);
			sp.setType(type);

			this.pauseDataThread();
			try {
				this.client.setSignalParameters(sp);
			} catch (Exception e) {
				e.printStackTrace();
				this.lblMessagejlabel.setText("Error changing parameters: "
						+ e.getMessage());
			}
			this.resumeDataThread();
		} else {
			this.lblMessagejlabel.setText("RMI client not available...");
		}
	}

	public void updateSignalParameters(){
		if (client != null) {
			try {
				SignalParameters sp = client.getSignalParameters();
				DecimalFormat df = new DecimalFormat("#.###");
				DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
				dfs.setDecimalSeparator('.');
				df.setDecimalFormatSymbols(dfs);
				this.amplitudeTextField.setText(df.format(sp.getAmplitude()));
				this.frequencytextField.setText(df.format(sp.getFrequency()));
				if (sp.getType() == WaveForm.SQUARE){
					this.waveFormsComboBox.setSelectedIndex(1);
				} else if (sp.getType() == WaveForm.TRIANGLE){
					this.waveFormsComboBox.setSelectedIndex(2);
				} else {
					this.waveFormsComboBox.setSelectedIndex(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
				this.lblMessagejlabel
						.setText("Error getting signal parameters: "
								+ e.getMessage());
			}
		} else {
			this.lblMessagejlabel.setText("RMI client not available...");
		}
	}
	
	public void startSignalGenerator() {
		if (this.client != null) {
			try {
				if (client.start()) {
					this.lblMessagejlabel.setText("Signal generator started");
					this.btnStartButton.setEnabled(false);
					this.btnStopButton.setEnabled(true);
					startDataThread();
				} else {
					this.lblMessagejlabel
							.setText("Signal generator not started");
				}
			} catch (Exception e) {
				e.printStackTrace();
				this.lblMessagejlabel
						.setText("Error starting signal generator: "
								+ e.getMessage());
			}
		} else {
			this.lblMessagejlabel.setText("RMI client not available...");
		}
	}

	public void stopSignalGenerator() {
		if (this.client != null) {
			try {
				stopDataThread();
				if (client.stop()) {
					this.lblMessagejlabel.setText("Signal generator stopped");
					this.btnStartButton.setEnabled(true);
					this.btnStopButton.setEnabled(false);
				} else {
					this.lblMessagejlabel
							.setText("Signal generator not stopped");
				}
			} catch (Exception e) {
				e.printStackTrace();
				this.lblMessagejlabel
						.setText("Error stopping signal generator: "
								+ e.getMessage());
			}
		} else {
			this.lblMessagejlabel.setText("RMI client not available...");
		}
	}
	
	private void closeFrame(){
		if (thread!=null && thread.isAlive()){
			stopSignalGenerator();
		}
	}

	private ChartPanel createPlot() {
		// Create dataset
		dataSerie = new XYSeries("Signal values");
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(dataSerie);
		// Create plot
		JFreeChart chart = ChartFactory.createXYLineChart("Signal data",
				 "Time (s)", "Value", dataset, PlotOrientation.VERTICAL, false,
				false, false);
		ChartPanel p = new ChartPanel(chart);
		p.setPreferredSize(new Dimension(550, 350));
		return p;
	}

	public void addData(SignalData d) {	
		dataSerie.add(d.getTime(),d.getOutput());
		int n = dataSerie.getItemCount();
		if (n>NMAX){
			dataSerie.remove(0);
		}
	}

	@Override
	public void run() {
		SignalData sd = null;
		while (getData) {
			try {
				if (client != null && !this.settingParameters) {
					sd = this.client.getSignalValue();
					this.addData(sd);
				}
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void changeDelay(){
		// Get 
		delay = delaySlider.getValue();
		double d = delay/1000.0;
		this.lblDelayLabel.setText(d + " seconds");
	}
	private void startDataThread(){
		if (thread==null){
			// Clear series
			dataSerie.clear();
			getData = true;
			thread = new Thread(this, "Data thread get data");
			thread.start();
		}
	}
	
	private void stopDataThread(){
		if (thread!=null){
			getData = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			thread = null;
		}
	}
	
	private void pauseDataThread(){
		this.settingParameters = true;
	}
	
	private void resumeDataThread(){
		this.settingParameters = false;
	}
}
