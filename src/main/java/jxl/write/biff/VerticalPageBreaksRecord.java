package jxl.write.biff;

import com.android.engineeringmode.functions.Light;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class VerticalPageBreaksRecord extends WritableRecordData {
    private int[] columnBreaks;

    public VerticalPageBreaksRecord(int[] breaks) {
        super(Type.VERTICALPAGEBREAKS);
        this.columnBreaks = breaks;
    }

    public byte[] getData() {
        byte[] data = new byte[((this.columnBreaks.length * 6) + 2)];
        IntegerHelper.getTwoBytes(this.columnBreaks.length, data, 0);
        int pos = 2;
        for (int twoBytes : this.columnBreaks) {
            IntegerHelper.getTwoBytes(twoBytes, data, pos);
            IntegerHelper.getTwoBytes(Light.MAIN_KEY_MAX, data, pos + 4);
            pos += 6;
        }
        return data;
    }
}
