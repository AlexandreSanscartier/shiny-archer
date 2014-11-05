package tools;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Canvas;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Button;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SpriteCreater {

	private JFrame frmAnimationCreator;
	private JTextField txtName;
	private JTextField txtAnimationKey;
	private JTextField txtAnimationImageWidth;
	private JTextField txtAnimationImageHeight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpriteCreater window = new SpriteCreater();
					window.frmAnimationCreator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SpriteCreater() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAnimationCreator = new JFrame();
		frmAnimationCreator.setTitle("Sprite Creater");
		frmAnimationCreator.setBounds(100, 100, 681, 463);
		frmAnimationCreator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmAnimationCreator.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mnFile.add(mntmNew);
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mnFile.add(mntmLoad);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		frmAnimationCreator.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 11, 46, 14);
		frmAnimationCreator.getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(46, 8, 86, 20);
		frmAnimationCreator.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Animation Sets", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(350, 11, 305, 382);
		frmAnimationCreator.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnPreviewAnimation = new JButton("Preview");
		btnPreviewAnimation.setBounds(224, 348, 71, 23);
		panel.add(btnPreviewAnimation);
		
		JButton btnNewAnimation = new JButton("New");
		btnNewAnimation.setBounds(10, 348, 53, 23);
		panel.add(btnNewAnimation);
		
		JLabel lblName_1 = new JLabel("Key:");
		lblName_1.setBounds(10, 23, 46, 14);
		panel.add(lblName_1);
		
		txtAnimationKey = new JTextField();
		txtAnimationKey.setBounds(52, 20, 46, 20);
		panel.add(txtAnimationKey);
		txtAnimationKey.setColumns(10);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(10, 216, 285, 121);
		panel.add(list);
		
		JButton btnUploadAnimationImage = new JButton("Upload Image");
		btnUploadAnimationImage.setBounds(196, 183, 99, 23);
		panel.add(btnUploadAnimationImage);
		
		JLabel lblWidth = new JLabel("Width:");
		lblWidth.setBounds(10, 51, 46, 14);
		panel.add(lblWidth);
		
		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setBounds(112, 51, 46, 14);
		panel.add(lblHeight);
		
		txtAnimationImageWidth = new JTextField();
		txtAnimationImageWidth.setEditable(false);
		txtAnimationImageWidth.setBounds(52, 48, 50, 20);
		panel.add(txtAnimationImageWidth);
		txtAnimationImageWidth.setColumns(10);
		
		txtAnimationImageHeight = new JTextField();
		txtAnimationImageHeight.setEditable(false);
		txtAnimationImageHeight.setColumns(10);
		txtAnimationImageHeight.setBounds(158, 48, 71, 20);
		panel.add(txtAnimationImageHeight);
		
		JButton btnAddImage = new JButton("Add Image");
		btnAddImage.setBounds(10, 182, 89, 23);
		panel.add(btnAddImage);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(69, 348, 69, 23);
		panel.add(btnSave);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.setBounds(147, 348, 67, 23);
		panel.add(btnLoad);
		frmAnimationCreator.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblName, txtName}));
	}
}
