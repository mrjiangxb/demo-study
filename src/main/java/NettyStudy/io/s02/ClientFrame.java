package NettyStudy.io.s02;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientFrame extends Frame {

    private static final ClientFrame INSTANCE = new ClientFrame();

    TextArea textArea = new TextArea();
    TextField textField = new TextField();

    Client client = null;

    public static ClientFrame getInstance() {
        return INSTANCE;
    }

    private ClientFrame () {
        this.setSize(600, 400);
        this.setLocation(100,20);
        this.add(textArea, BorderLayout.CENTER);
        this.add(textField, BorderLayout.SOUTH);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 把字符串发到服务器
                client.send(textField.getText());
                // textArea.setText(textArea.getText() + textField.getText());
                textField.setText("");
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.closeConnect();
                System.exit(0);
                System.out.println("客户端已退出");
            }
        });

    }


    // 连接客户端
    private void connectToServer() {
        client = new Client();
        client.connect();
    }

    public void updateText(String msgAccept){
        this.textArea.setText(
                textArea.getText()+System.getProperty("line.separator")+msgAccept);
    }

    public static void main(String[] args) {
        ClientFrame frame = ClientFrame.INSTANCE;
        frame.setVisible(true);
        frame.connectToServer(); // 连接客户端
    }

}