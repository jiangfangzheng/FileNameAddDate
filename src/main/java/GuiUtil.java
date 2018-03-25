import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @author Sandeepin
 * 2017/12/14 0014
 */
public class GuiUtil {

    // 使窗口居中
    public static void setFrameCenter(JFrame frame,boolean resizable) {
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        frame.setLocation((screenWidth - frame.getWidth()) / 2, (screenHeight - frame.getHeight()) / 2);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 窗口不可变大变小
        frame.setResizable(resizable);
        URL iconUrl = GuiUtil.class.getClassLoader().getResource("img/rename.png");
        if (iconUrl!=null){
            ImageIcon icon = new ImageIcon(iconUrl);
            frame.setIconImage(icon.getImage());
        }
    }

    // 使窗口居中
    public static void setFrameCenter(JFrame frame) {
        setFrameCenter(frame, true);
    }

    // 系统风格界面
    public static void setSystemStyle() {
        // 设置字体
        Font f = new Font("微软雅黑", Font.PLAIN, 14);
        String[] names = {
                "Label", "CheckBox", "PopupMenu", "MenuItem", "CheckBoxMenuItem",
                "JRadioButtonMenuItem", "ComboBox", "Button", "Tree", "ScrollPane",
                "TabbedPane", "EditorPane", "TitledBorder", "Menu", "TextArea",
                "OptionPane", "MenuBar", "ToolBar", "ToggleButton", "ToolTip",
                "ProgressBar", "TableHeader", "Panel", "List", "ColorChooser",
                "PasswordField", "TextField", "Table", "Label", "Viewport",
                "RadioButtonMenuItem", "RadioButton", "DesktopPane", "InternalFrame"
        };
        for (String item : names) {
            UIManager.put(item + ".font", f);
        }
        try {
            // Windows风格
            if(UIManager.getLookAndFeel().isSupportedLookAndFeel()){
                String platform = UIManager.getSystemLookAndFeelClassName();
                if (!UIManager.getLookAndFeel().getName().equals(platform)) {
                    try {
                        UIManager.setLookAndFeel(platform);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
