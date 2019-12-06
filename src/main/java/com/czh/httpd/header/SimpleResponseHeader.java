package com.czh.httpd.header;

import com.czh.httpd.App;
import com.czh.httpd.enums.HttpStatus;

/**
 * @author czh
 */
public class SimpleResponseHeader extends BaseResponseHeader {

    public SimpleResponseHeader() {
    }

    public SimpleResponseHeader(String responseHeaderString) {
        super(responseHeaderString);
    }

    @Override
    public void setFirstLine(String line) throws IllegalArgumentException {
        String[] statusLineArray = line.split(App.SPACE);
        int statusLineLength = statusLineArray.length;
        if (statusLineLength != 3) {
            throw new IllegalArgumentException("相应状态行格式错误, " + line);
        }
        this.httpVersion = statusLineArray[0];
        this.httpStatus = HttpStatus.valueOf(Integer.parseInt(statusLineArray[1]));
//        this.httpStatus = statusLineArray[1];
//        this.statusName = statusLineArray[2];
    }

    @Override
    public String getFirstLine() {
        return this.httpVersion + App.SPACE + this.httpStatus.value() + App.SPACE + this.httpStatus.getReasonPhrase();
    }

    @Override
    public String toString() {
        return this.build();
    }
}
