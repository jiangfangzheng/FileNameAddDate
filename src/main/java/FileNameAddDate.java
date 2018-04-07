import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author Sandeepin
 * 2018/3/25 0025
 */
public class FileNameAddDate extends JFrame implements ActionListener {

    private JButton button1;
    private JButton button2;
    private JLabel label1;
    private JLabel label2;

    private String path;

    FileNameAddDate() {
        GuiUtil.setSystemStyle();
        this.setTitle("重命名 By jfz");
        this.setSize(400, 130);
        GuiUtil.setFrameCenter(this, false);
        this.setVisible(true);
        Container con = this.getContentPane();
        con.setLayout(null);

        // 选择
        button1 = new JButton("选择");
        button1.setFocusPainted(false);
        button1.addActionListener(this);
        button1.setActionCommand("open");
        button1.setSize(80, 30);
        button1.setLocation(10, 10);
        // 加日期
        button2 = new JButton("加日期");
        button2.setFocusPainted(false);
        button2.addActionListener(this);
        button2.setActionCommand("adddate");
        button2.setSize(80, 30);
        button2.setLocation(100, 10);
        // 处理结果
        label2 = new JLabel("处理结果：未处理");
        label2.setSize(200, 30);
        label2.setLocation(190, 10);
        // 文件目录
        label1 = new JLabel("文件目录：无");
        label1.setSize(400, 30);
        label1.setLocation(10, 50);

        con.setBackground(Color.white);
        con.add(button1);
        con.add(button2);
        con.add(label2);
        con.add(label1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 选择
        if ("open".equals(e.getActionCommand())) {
            JFileChooser jf = new JFileChooser();
            // 只选择文件夹
            jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            // 显示打开的文件对话框
            jf.showOpenDialog(this);
            // 使用文件类获取选择器选择的文件
            if (jf.getSelectedFile() != null) {
                File f = jf.getSelectedFile();
                // 返回路径名
                String s = f.getAbsolutePath();
                System.out.println(s);
                // JOptionPane弹出对话框类，显示绝对路径名
//                JOptionPane.showMessageDialog(this, s, "标题", JOptionPane.WARNING_MESSAGE);
                path = s;
                label1.setText("文件目录：" + s);
                label2.setText("处理结果：未处理");
            }
        }
        // 加日期
        if ("adddate".equals(e.getActionCommand())) {
            boolean b = RenameUtil.addFileDataToFileName(path);
            if (b) {
                label2.setText("处理结果：完毕！");
            } else {
                label2.setText("处理结果：失败！");
            }

        }
    }
}
