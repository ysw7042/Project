package casino;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ProjectCasino extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JButton Addbtn, Upbtn, Delbtn, Canbtn,Sheabtn;
	private JLabel label1, label2, label3, label4, label5;
	private Object[] constructor = { "회원번호", "회원이름", "생년월일", "회원등급", "출입날짜" };
	private JPanel panel, panel1, panel2, panel3;
	DefaultTableModel tableModel = new DefaultTableModel(constructor, 0);
	JTable table = new JTable(tableModel);
	private JTextField Cnofield, Cnafield, Cbrfield, Cgrfield, Cenfield;


	public ProjectCasino(String title) {
		super(title);


		panel1 = new JPanel(new GridLayout(5, 1, 3, 3));
		panel1.setBounds(0, 39, 75, 383);
		panel2 = new JPanel(new BorderLayout());
		panel2.setBounds(75, 39, 625, 383);
		panel3 = new JPanel();//Absolute Layout
		panel3.setBounds(0, 0, 700, 39);

		Addbtn = new JButton("추가");
		Upbtn = new JButton("변경");
		Delbtn = new JButton("삭제");
		Sheabtn = new JButton("검색");
		Canbtn = new JButton("취소");

		panel1.add(Addbtn);
		panel1.add(Upbtn);
		panel1.add(Delbtn);
		panel1.add(Sheabtn);
		panel1.add(Canbtn);

		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		JScrollPane pane = new JScrollPane(table);

		panel2.add(pane, BorderLayout.CENTER);

		panel = new JPanel();
		pane.setColumnHeaderView(panel);
		panel.setLayout(null);
		panel3.setLayout(null);
		

		label1 = new JLabel("회원번호");
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setBounds(12, 1, 59, 19);
		panel3.add(label1);
		Cnofield = new JTextField("", 15);
		Cnofield.setBounds(70, 0, 130, 19);

		panel3.add(Cnofield);
		label2 = new JLabel("회원이름");
		label2.setHorizontalAlignment(JLabel.CENTER);
		label2.setBounds(262, 1, 59, 19);
		panel3.add(label2);
		Cnafield = new JTextField("", 15);
		Cnafield.setBounds(320, 0, 130, 19);

		panel3.add(Cnafield);
		label3 = new JLabel("회원등급");
		label3.setHorizontalAlignment(JLabel.CENTER);
		label3.setBounds(497, 1, 59, 19);
		panel3.add(label3);

		Cgrfield = new JTextField("", 15);
		Cgrfield.setBounds(551, 0, 130, 19);
		panel3.add(Cgrfield);


		Addbtn.addActionListener(this);
		Upbtn.addActionListener(this);
		Delbtn.addActionListener(this);
		Sheabtn.addActionListener(this);
		Canbtn.addActionListener(this);

		Container cont = getContentPane();
		getContentPane().setLayout(null);

		cont.add(panel1);
		cont.add(panel2);
		cont.add(panel3);

		
		Cbrfield = new JTextField("", 15);
		Cbrfield.setBounds(320, 20, 130, 19);
		panel3.add(Cbrfield);

		label4 = new JLabel("생년월일");
		label4.setHorizontalAlignment(JLabel.CENTER);
		label4.setBounds(262, 21, 59, 19);
		panel3.add(label4);

		label5 = new JLabel("출입날짜");
		label5.setHorizontalAlignment(JLabel.CENTER);
		label5.setBounds(12, 20, 59, 19);
		panel3.add(label5);

		Cenfield = new JTextField("", 15);
		Cenfield.setBounds(70, 19, 130, 19);
		panel3.add(Cenfield);

		initialize();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(600, 600, 700, 450);
		setLocationRelativeTo(null);
		setVisible(true);
		displayAllcustomer();
	}

	public static void main(String[] args) {
		new ProjectCasino("카지노 회원 관리 프로그램");
	}

	public void initialize() {
		Cnofield.setEditable(false);
		Cnafield.setEditable(false);
		Cgrfield.setEditable(false);
		Cenfield.setEditable(false);
		Cbrfield.setEditable(false);
	}
	
	public void clear() {
		Cnofield.setText("");
		Cnafield.setText("");
		Cgrfield.setText("");
		Cenfield.setText("");
		Cbrfield.setText("");
	}
	
	public void Enable() {
		Addbtn.setEnabled(true);
		Upbtn.setEnabled(true);
		Delbtn.setEnabled(true);
		Canbtn.setEnabled(true);
		Sheabtn.setEnabled(true);
	}
	
	public void ResetDisplay() {
		clear();
		initialize();
		Enable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton src = (JButton) e.getSource();
		Addbtn.setEnabled(true);
		Upbtn.setEnabled(true);
		Delbtn.setEnabled(true);
		Canbtn.setEnabled(true);
		Sheabtn.setEnabled(true);

		if(Addbtn==(src)) {
			Upbtn.setEnabled(false);
			Delbtn.setEnabled(false);
			Sheabtn.setEnabled(false);
		} else if (Upbtn==(src)) {
			Addbtn.setEnabled(false);
			Delbtn.setEnabled(false);
			Sheabtn.setEnabled(false);
		} else if (Delbtn==(src)) {
			Addbtn.setEnabled(false);
			Upbtn.setEnabled(false);
			Sheabtn.setEnabled(false);
		} else if (Sheabtn==(src)) {
			Addbtn.setEnabled(false);
			Upbtn.setEnabled(false);
			Delbtn.setEnabled(false);
		} else if (Canbtn==(src)) {
			Upbtn.setEnabled(false);
			Delbtn.setEnabled(false);
			Addbtn.setEnabled(false);
		} 
		
		
		if(Addbtn==(src)) {
			Cnafield.setEditable(true);
			Cnofield.setEditable(true);
			Cbrfield.setEditable(true);
			Cgrfield.setEditable(true);
			Cenfield.setEditable(true);
		} 
		
		if(Delbtn==(src)) {
			Cnofield.setEditable(true);
		}
		if(Upbtn==(src)) {
			Cnofield.setEditable(true);
			Cnafield.setEditable(true);
			Cbrfield.setEditable(true);
			Cgrfield.setEditable(true);
			Cenfield.setEditable(true);
		}  
		
		if(Sheabtn==(src)) {
			Cnofield.setEditable(true);
			Cgrfield.setEditable(true);
		}
		
		if(Canbtn==(src)) {
			displayAllcustomer();
		}
		
		if(Cnofield==null) {
			JOptionPane.showMessageDialog(this, "입력을 해주세요.");
		}
		
		if(Addbtn==(src)) {
			addCustomer();
		} else if(Delbtn==(src)) {
			deleteCustomer();
		} else if(Upbtn==(src)) {
			updateCustomer();
		} else if(Sheabtn==(src)) {
			searchCustomer();
		} else if(Canbtn==(src)) {
			ResetDisplay();
		}
	
	}

	public void displayAllcustomer() {
		List<CasinoDTO> customerList = CasinoDAO.getDao().selectAllCutomerList();

		if (customerList.isEmpty()) {
			JOptionPane.showMessageDialog(this, "회원정보가 없습니다.");
			return;
		}

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		for (int i = model.getRowCount();i>0;i--) {
			model.removeRow(0);
		}

		for (CasinoDTO customer:customerList) {
			Vector<Object> rowValue = new Vector<Object>();
			rowValue.add(customer.getCno());
			rowValue.add(customer.getCname());
			rowValue.add(customer.getCbirth());
			rowValue.add(customer.getCgrade());
			rowValue.add(customer.getCenter());
			model.addRow(rowValue);
		}
	}

	public void addCustomer() {
		String cnoTemp = Cnofield.getText();
		if (cnoTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "회원번호를 입력하세요.");
			Cnofield.requestFocus();
			return;
		}

		String CnoReg = "\\d{6}";
		if (!Pattern.matches(CnoReg, cnoTemp)) {
			JOptionPane.showMessageDialog(this, "회원번호는 6자리의 숫자로 입력해주세요.");
			Cnofield.requestFocus();
			return;
		}

		int cno = Integer.parseInt(cnoTemp);
		
		if(CasinoDAO.getDao().selectNoCutomer(cno)!=null) {
			JOptionPane.showMessageDialog(this, "해당 회원번호는 이용 중입니다.");
			Cnofield.requestFocus();
			return;
		}
		
		String cname = Cnafield.getText();
		
		if(cname.equals("")) {
			JOptionPane.showMessageDialog(this, "회원이름을 입력해주세요.");
			Cnafield.requestFocus();
			return;
		}
		
		String cnameReg = "([가-힣|(?!)a-z|A-Z]){2,7}";
		if(!Pattern.matches(cnameReg, cname)) {
			JOptionPane.showMessageDialog(this, "이름은 2~5범위의 영문자 혹은 한글만 입력해주세요.");
			Cnafield.requestFocus();
			return;
		}
		
	
		String cgrade = Cgrfield.getText();
		
		if(cgrade.equals("")) {
			JOptionPane.showMessageDialog(this, "등급을 입력해주세요");
			Cgrfield.requestFocus();
			return;
		}
		
		
		String cgradeReg = "(Gold|Silver|Vip)";
		if(!Pattern.matches(cgradeReg, cgrade)) {
			JOptionPane.showMessageDialog(this, "등급은 Gold / Silver / Vip 중에 입력해주세요. ");
			Cgrfield.requestFocus();
			return;
		}
		
		
		String center = Cenfield.getText();
		
		if(center.equals("")) {
			JOptionPane.showMessageDialog(this, "출입 날짜를 입력하세요");
			Cenfield.requestFocus();
			return;
		}
		
		String centerReg = "(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";
		if(!Pattern.matches(centerReg, center)) {
			JOptionPane.showMessageDialog(this, "출입 날짜는 형식에 맞게 입력해주세요.");
			Cenfield.requestFocus();
			return;
		}
		
		String cbirth = Cbrfield.getText();
		
		if(cbirth.equals("")) {
			JOptionPane.showMessageDialog(this, "생년월일을 입력하세요");
			Cbrfield.requestFocus();
			return;
		}
		
		String cbrReg = "(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";
		if(!Pattern.matches(cbrReg, cbirth)) {
			JOptionPane.showMessageDialog(this, "생년월일은 날짜 형식에 맞게 입력해주세요.");
			Cbrfield.requestFocus();
			return;
		}
		
		CasinoDTO customer = new CasinoDTO();
		customer.setCno(cno);
		customer.setCname(cname);
		customer.setCgrade(cgrade);
		customer.setCenter(center);
		customer.setCbirth(cbirth);
		
		int rows = CasinoDAO.getDao().insertCustomer(customer);
		
		JOptionPane.showMessageDialog(this, rows+"명의 회원정보가 추가되었습니다.");
		
		if(rows>=1) {
			ResetDisplay();
		}
		
		displayAllcustomer();
		initialize();
	}
	
	public void deleteCustomer() {
		String CnoTemp = Cnofield.getText();
		if(CnoTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "삭제 할 회원번호를 기입해주세요.");
			Cnofield.requestFocus();  
			return;
		}
		
		String cnoReg = "\\d{6}";
		if(!Pattern.matches(cnoReg, CnoTemp)) {
			JOptionPane.showMessageDialog(this, "회원번호는 6자리 숫자로만 입력 가능합니다.");
			Cnofield.requestFocus();
			return;
		}
		
		int cno = Integer.parseInt(CnoTemp);
		
		int rows = CasinoDAO.getDao().deleteCutomer(cno);
		
		if(rows>0) {
			JOptionPane.showMessageDialog(this, rows +"명의 회원정보를 삭제하였습니다.");
			displayAllcustomer();
		} else {
			JOptionPane.showMessageDialog(this, "삭제 할 회원을 다시 한번 확인해주세요.");
		}
		ResetDisplay();
	}
		
	public void updateCustomer() {
			String CnoTemp = Cnofield.getText();
			if(CnoTemp.equals("")) {
				JOptionPane.showMessageDialog(this, "변경 할 회원번호를 기입해주세요.");
				Cnofield.requestFocus();  
				return;
			}
			
			String cnoReg = "\\d{6}";
			if(!Pattern.matches(cnoReg, CnoTemp)) {
				JOptionPane.showMessageDialog(this, "회원번호는 6자리 숫자로만 입력 가능합니다.");
				Cnofield.requestFocus();
				return;
			}
			
			int cno = Integer.parseInt(CnoTemp);
			
			CasinoDTO customer = CasinoDAO.getDao().selectNoCutomer(cno); 
			
			if(customer==null) {
				JOptionPane.showMessageDialog(this, "변경하고자 하는 번호의 회원이 존재하지 않습니다.");
				Cnofield.requestFocus();
				Cnofield.setText(""); 
				return;
			}
			
			String cname = Cnafield.getText();
			
			if(cname.equals("")) {
				JOptionPane.showMessageDialog(this, "회원이름을 입력하세요.");
				Cnafield.requestFocus();
				return;
			}
			
			String cnameReg = "([가-힣|(?!)a-z|A-Z]){2,7}";
			if(!Pattern.matches(cnameReg, cname)) {
				JOptionPane.showMessageDialog(this, "이름은 2~5범위의 영문자 혹은 한글만 입력해주세요.");
				Cnafield.requestFocus();
				return;
			}
			
			String cgrade = Cgrfield.getText();
			
			if(cgrade.equals("")) {
				JOptionPane.showMessageDialog(this, "등급을 입력해주세요");
				Cgrfield.requestFocus();
				return;
			}
			
			
			String cgradeReg = "(Gold|Silver|Vip)";
			if(!Pattern.matches(cgradeReg, cgrade)) {
				JOptionPane.showMessageDialog(this, "등급은 (G)Gold / (S)Silver / (V)Vip 중에 입력해주세요. ");
				Cgrfield.requestFocus();
				return;
			}
			
			
			String center = Cenfield.getText();
			
			if(center.equals("")) {
				JOptionPane.showMessageDialog(this, "출입 날짜를 입력하세요");
				Cenfield.requestFocus();
				return;
			}
			
			String centerReg = "(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";
			if(!Pattern.matches(centerReg, center)) {
				JOptionPane.showMessageDialog(this, "출입 날짜는 형식에 맞게 입력해주세요.");
				Cenfield.requestFocus();
				return;
			}
			
			String cbirth = Cbrfield.getText();
			
			if(cbirth.equals("")) {
				JOptionPane.showMessageDialog(this, "생년월일을 입력하세요");
				Cbrfield.requestFocus();
				return;
			}
			
			String cbrReg = "(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";
			if(!Pattern.matches(cbrReg, cbirth)) {
				JOptionPane.showMessageDialog(this, "생년월일은 날짜 형식에 맞게 입력해주세요.");
				Cbrfield.requestFocus();
				return;
			}
			
			CasinoDTO customer1 = new CasinoDTO();
			customer1.setCno(cno);
			customer1.setCname(cname);
			customer1.setCgrade(cgrade);
			customer1.setCenter(center);
			customer1.setCbirth(cbirth);
			
			int rows = CasinoDAO.getDao().updateCutomer(customer1);
			
			JOptionPane.showMessageDialog(this, rows+"명의 회원정보가 변경되었습니다.");
			
			if(rows>=1) {
				ResetDisplay();
			}
			
			displayAllcustomer();
			initialize();
			
			Cnofield.setText(customer1.getCno()+"");
			Cnafield.setText(customer1.getCname());
			Cgrfield.setText(customer1.getCgrade());
			Cbrfield.setText(customer1.getCbirth());
			Cenfield.setText(customer1.getCenter());
			
			ResetDisplay();
		}
	
	public void searchCustomer() {
		String CnoTemp = Cnofield.getText();
		
		if(CnoTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "검색 할 회원번호를 기입해주세요.");
			Cnofield.requestFocus();  
			return;
		}
		
		String cnoReg = "\\d{6}";
		if(!Pattern.matches(cnoReg, CnoTemp)) {
			JOptionPane.showMessageDialog(this, "회원번호는 6자리 숫자로만 입력 가능합니다.");
			Cnofield.requestFocus();
			return;
		}
		
		List<CasinoDTO> customerList = CasinoDAO.getDao().selectNameCutomerList(Integer.parseInt(CnoTemp));
	
		if(customerList.isEmpty()) {
			JOptionPane.showMessageDialog(this, "검색된 회원이 없습니다.");
			return;
		}
		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		for(int i=model.getRowCount();i>0;i--) {
			model.removeRow(0);
		}
		
		for(CasinoDTO customer:customerList) {
			Vector<Object> row = new Vector<Object>();
			row.add(customer.getCno());
			row.add(customer.getCname());
			row.add(customer.getCgrade());
			row.add(customer.getCbirth());
			row.add(customer.getCenter());
			model.addRow(row);
		}
		
		ResetDisplay();
	}
		
}
