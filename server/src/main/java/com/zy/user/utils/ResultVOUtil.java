package com.zy.user.utils;

import com.zy.user.enums.ResultEnum;
import com.zy.user.vo.ResultVO;

public class ResultVOUtil {

    public static ResultVO success(Object data){
        int code = 0;
        String message = "成功";
        return new ResultVO(code, message, data);
    }

    public static ResultVO success(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1);
        resultVO.setMessage("成功");
        return resultVO;
    }

    public static ResultVO error(ResultEnum resultEnum){
        return new ResultVO(1, "失败", resultEnum);
    }
}
