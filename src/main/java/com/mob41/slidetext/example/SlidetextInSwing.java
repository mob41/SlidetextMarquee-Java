package com.mob41.slidetext.example;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.mob41.slidetext.Slidetext;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JCheckBox;

public class SlidetextInSwing {

	private Slidetext st;
	private JFrame frame;
	private JTextField txtIAmA;
	private Timer timer1;
	private Timer timer2;
	private String[] frames;
	private int oldSpinner = 250;
	private int oldSlider = 250;
	private int index = 0;
	private ActionListener updateFrame = new ActionListener(){

		public void actionPerformed(ActionEvent e) {
			if (frames == null){
				if ((Integer) slots.getValue() <= 0){
					slots.setValue(1);
				}
				st = new Slidetext(txtIAmA.getText(), (Integer) slots.getValue());
				frames = st.getFrames(chckbxblanksLeft.isSelected(), chckbxblanksRight.isSelected());
			}
			if (index >= frames.length){
				System.err.println("=== Restart Frame ===");
				lblFrames.setForeground(Color.GREEN);
				index = 0;
				return;
			} else {
				lblFrames.setForeground(Color.BLACK);
			}
			lblFrames.setText("Frames: " + index + " / "+ frames.length);
			System.out.println(frames[index]);
			lblSlideText.setText(frames[index]);
			index++;
		}
		
	};
	private ActionListener updateSlidebarSpinner = new ActionListener(){

		public void actionPerformed(ActionEvent e) {
			int newslider = value_slider.getValue();
			int newspinner = (Integer) value_spinner.getValue();
			if (newslider <= 0 || newspinner <= 0){
				System.err.println("!!! Frame per ms cannot be 0 !!!");
				value_slider.setValue(250);
				value_spinner.setValue(250);
			}
			if (value_slider.getValue() != oldSlider){
				oldSlider = newslider;
				oldSpinner = newspinner;
				
				value_spinner.setValue(newslider);
				timer1.stop();
				System.err.println("=== Slider changed. " + newslider + " ===");
				timer1 = new Timer(newslider, updateFrame);
				timer1.start();
			} else if ((Integer) value_spinner.getValue() != oldSpinner){
				oldSlider = newslider;
				oldSpinner = newspinner;
				
				value_slider.setValue(newspinner);
				
				timer1.stop();
				System.err.println("=== Spinner changed. " + newslider + " ===");
				timer1 = new Timer(newslider, updateFrame);
				timer1.start();
			}
		}
		
	};
	private JSpinner slots;
	private JSpinner value_spinner;
	private JSlider value_slider;
	private JLabel lblSlideText;
	private JLabel lblFrames;
	private JCheckBox chckbxblanksLeft;
	private JCheckBox chckbxblanksRight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SlidetextInSwing window = new SlidetextInSwing();
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
	public SlidetextInSwing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Slidetext example");
		frame.setBounds(100, 100, 614, 423);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblSlideText = new JLabel("");
		lblSlideText.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlideText.setFont(new Font("Tahoma", Font.PLAIN, 53));
		
		lblFrames = new JLabel("Frames:");
		lblFrames.setHorizontalAlignment(SwingConstants.LEFT);
		
		value_slider = new JSlider();
		value_slider.setMinimum(10);
		value_slider.setMaximum(2000);
		value_slider.setValue(250);
		
		JLabel lblValue = new JLabel("Value:");
		
		value_spinner = new JSpinner();
		value_spinner.setValue(250);
		
		JLabel lblMs = new JLabel("ms");
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Slidetext generation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
						.addComponent(lblSlideText, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
						.addComponent(lblFrames, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblValue)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(value_spinner, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMs)
							.addGap(444))
						.addComponent(value_slider, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSlideText, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblFrames, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
					.addGap(26)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(value_slider, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblValue)
						.addComponent(value_spinner)
						.addComponent(lblMs))
					.addContainerGap())
		);
		
		JLabel lblSlidetext = new JLabel("Slidetext:");
		
		txtIAmA = new JTextField();
		txtIAmA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frames = null;
			}
		});
		txtIAmA.setText("I am a slide text!");
		txtIAmA.setColumns(10);
		
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frames = null;
			}
		});
		
		JLabel lblMaxSlots = new JLabel("Max. slots:");
		
		slots = new JSpinner();
		slots.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				frames = null;
			}
		});
		slots.setValue(20);
		
		chckbxblanksLeft = new JCheckBox("Blanks on the left");
		chckbxblanksLeft.setSelected(true);
		
		chckbxblanksRight = new JCheckBox("Blanks on the right");
		chckbxblanksRight.setSelected(true);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addGap(485)
							.addComponent(btnChange, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblSlidetext, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
											.addGap(7))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblMaxSlots, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
											.addGap(2)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(slots, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
										.addComponent(txtIAmA, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)))
								.addComponent(chckbxblanksLeft, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
								.addComponent(chckbxblanksRight, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSlidetext)
						.addComponent(txtIAmA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaxSlots)
						.addComponent(slots, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxblanksLeft, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(2)
					.addComponent(chckbxblanksRight)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnChange, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
		
		timer1 = new Timer(250, updateFrame);
		timer2 = new Timer(250, updateSlidebarSpinner);
		timer1.start();
		timer2.start();
	}
}
