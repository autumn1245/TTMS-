package view;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import entity.Studio;

import java.util.List;
import java.util.Iterator;
import service.StudioSrv;

class StudioTableMouseListener extends MouseAdapter {

	private JTable jt;
	static Studio hall;

	public static Studio getHall() {
		return hall;
	}

	public StudioTableMouseListener(JTable jt, Object[] number, Studio hall) {
		this.hall = hall;
		this.jt = jt;
	}

	// �������кţ�����ѡ�е��������θ��� hall ��̬�����Ա㴫��ֵ�����޸��������޸�
	public void mouseClicked(MouseEvent event) {
		int row = jt.getSelectedRow();
		hall.setID(Integer.parseInt(jt.getValueAt(row, 0).toString()));
		hall.setName(jt.getValueAt(row, 1).toString());
		hall.setRowCount(Integer.parseInt(jt.getValueAt(row, 2).toString())); // 0
		hall.setColCount(Integer.parseInt(jt.getValueAt(row, 3).toString()));
		if(jt.getValueAt(row, 4)!=null)
			hall.setIntroduction(jt.getValueAt(row, 4).toString());
		else
			hall.setIntroduction("");
		System.out.println(jt.getValueAt(row, 1).toString());
	}
}

class StudioTable {

	static Studio hall;
	private JTable jt = null;

	public StudioTable(Studio hall) {
		this.hall = hall;
	}

	// ����JTable
	public void createTable(JScrollPane jp, Object[] columnNames,
			List<Studio> stuList) {
		try {

			Object data[][] = new Object[stuList.size()][columnNames.length];

			Iterator<Studio> itr = stuList.iterator();
			int i = 0;
			while (itr.hasNext()) {

				Studio stu = itr.next();
				data[i][0] = Integer.toString(stu.getID());
				data[i][1] = stu.getName();
				data[i][2] = Integer.toString(stu.getRowCount());
				data[i][3] = Integer.toString(stu.getColCount());
				data[i][4] = stu.getIntroduction();				
				i++;
			}

			// ����JTable
			jt = new JTable(data, columnNames);
			jt.setBounds(0, 0, 700, 450);

			// �������������������ѡ��
			StudioTableMouseListener tml = new StudioTableMouseListener(jt,
					columnNames, hall);
			jt.addMouseListener(tml);

			// ���ÿɵ����п�
			jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

			jp.add(jt);
			jp.setViewportView(jt);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class StudioMgrUI extends JFrame implements ActionListener {
	static Studio hall;

	// ������ʾ
	private JLabel ca1 = null;

	// �����ű��Ĺ����ؼ�
	private JScrollPane jsc;

	// ���ҵ���ʾ�����
	private JLabel hint;
	private JTextField input;

	// ���ң��༭��ɾ����ť

	private JButton btnAdd, btnEdit, btnDel, btnQuery;

	public StudioMgrUI(Studio hall) {
		this.hall = hall;

		ca1 = new JLabel("�ݳ�������");
		ca1.setBounds(280, 6, 480, 26);

		jsc = new JScrollPane();
		jsc.setBounds(50, 40, 700, 450);
		this.add(jsc);

		hint = new JLabel("�������ݳ�������:");
		hint.setBounds(60, 500, 150, 30);
		this.add(hint);

		input = new JTextField();
		input.setBounds(300, 500, 120, 30);
		this.add(input);

		// ���� ��ɾ���ͱ༭�İ�ť�����к�����ص��¼�����
		btnQuery = new JButton("����");
		btnQuery.addActionListener(this);
		btnQuery.setBounds(440, 500, 60, 30);
		this.add(btnQuery);

		btnAdd = new JButton("���");
		btnAdd.addActionListener(this);
		btnAdd.setBounds(520, 500, 60, 30);
		this.add(btnAdd);
		
		btnEdit = new JButton("�༭");
		btnEdit.addActionListener(this);
		btnEdit.setBounds(600, 500, 60, 30);
		this.add(btnEdit);
		
		btnDel = new JButton("ɾ��");
		btnDel.addActionListener(this);
		btnDel.setBounds(680, 500, 60, 30);
		this.add(btnDel);

		this.add(ca1);
		this.setLayout(null);
		showTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnQuery) {
			if (!input.getText().equals("")) {
				/*
				 * String sql =
				 * " select * from hall where hall_descp = '"+input.
				 * getText()+"'"; HallTableModel tms = new HallTableModel(hall);
				 */
				jsc.repaint();
				Object[] in = { "id", "name", "row", "column", "hall_descp" };
				// tms.createTable(jsc, in, sql);

			} else {
				JOptionPane.showMessageDialog(null, "δ�����κ�����");
			}
		} else if (e.getSource() == btnAdd) {
			 StudioEditUI a = new StudioEditUI(this,false,null);
			 a.toFront();
			 a.setModal(true);
			 a.setVisible(true);
			 if(a.getReturnStatus()){
				 showTable();
			 }
			 
		} else if (e.getSource() == btnEdit) {
			StudioEditUI a = new StudioEditUI(this,true,hall);
			 a.toFront();
			 a.setModal(true);
			 a.setVisible(true);
			 if(a.getReturnStatus()){
				 showTable();
			 }			 
			 
		} else if (e.getSource() == btnDel) {
			int confirm = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����ѡ��", "ɾ��",
					JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				 StudioSrv stuSrv = new StudioSrv();
				 stuSrv.delete(hall.getID());			
				 showTable();
			}
		}
	}

	public void showTable() {
		StudioTable tms = new StudioTable(hall);
		Object[] in = { "id", "name", "row", "column", "hall_descp" };
		List<Studio> stuList = new StudioSrv().FetchAll();

		tms.createTable(jsc, in, stuList);

		jsc.repaint();
	}

	public static void main(String[] args) {
		StudioMgrUI frmStuMgr = new StudioMgrUI(new Studio());
		frmStuMgr.setLayout(null);
		frmStuMgr.setBounds(0, 0, 800, 600);
		frmStuMgr.setSize(800, 650);
		frmStuMgr.setVisible(true);
	}
}
