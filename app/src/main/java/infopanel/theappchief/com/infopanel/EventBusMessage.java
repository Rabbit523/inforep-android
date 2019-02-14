package infopanel.theappchief.com.infopanel;



public class EventBusMessage {
    public static class MessageType {
        /**
         */
        public final static int CHANGE_VIDEO = 0X01;  //手环数据
        public final static int PLAY_VIDEO = 0x02;
        public final static int OPEN_EMERGENCY = 0x03;
        public final static int FROM_VIDEO = 0x04;
    }



    private int messageType; //数据类型
    private String json;
    private Object object;


    public EventBusMessage(int messageType, String json) {
        this.messageType = messageType;
        this.json = json;
    }

    public EventBusMessage(int messageType) {
        this.messageType = messageType;
    }

    public EventBusMessage(int messageType, Object data) {
        this.messageType = messageType;
        this.object = data;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }


    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
