package NettyStudy.io.s01;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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