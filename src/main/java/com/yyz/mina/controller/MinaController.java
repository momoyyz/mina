package com.yyz.mina.controller;

import com.yyz.mina.common.enums.RunStatusEnum;
import com.yyz.mina.controller.vo.ResultResponse;
import com.yyz.mina.service.MinaServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api("shine测试接口")
public class MinaController {

    @Autowired
    MinaServer minaServer;

    private final static Logger log = Logger.getLogger(MinaController.class);

    /**
     * 开启服务端
     * @param port
     * @return
     */
    @GetMapping("openServer")
    @ApiOperation("启动服务器接口")
    @ApiImplicitParam(name = "port",value = "端口")
    public ResultResponse<Boolean> openServer(Integer port) {
        System.out.println("*****服务端准备开启*****");
        ResultResponse<Boolean> resultResponse=new ResultResponse<>();
        try {
            //入参检查
            if(!StringUtils.isEmpty(port)){
                resultResponse.setSuccessData(minaServer.openServer(port));
            }else{
                resultResponse.setError(RunStatusEnum.ARG_NULL_ERROR);
                log.error(RunStatusEnum.ARG_NULL_ERROR.getMsg());
            }
        }catch (Exception e){
            log.error(RunStatusEnum.SERVER_ERROR.getMsg(),e);
        }
        return resultResponse;

    }

    /**
     * 开启客户端
     * @param host
     * @param port
     * @return
     */
    @GetMapping("openClient")
    @ApiOperation("启动客户端接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "host",value = "地址"),
            @ApiImplicitParam(name = "port",value = "端口")})
    public ResultResponse<Boolean> openClient(String host, Integer port) {
        System.out.println("*****客户端准备开启*****");
        ResultResponse<Boolean> resultResponse=new ResultResponse<>();
        try {
            //入参检查
            if(!StringUtils.isEmpty(port)&&!StringUtils.isEmpty(host)){
                resultResponse.setSuccessData(minaServer.openClient(host,port));
            }else{
                resultResponse.setError(RunStatusEnum.ARG_NULL_ERROR);
                log.error(RunStatusEnum.ARG_NULL_ERROR.getMsg());
            }

        }catch (Exception e){
            resultResponse.setError(RunStatusEnum.CLIENT_ERROR);
            log.error(RunStatusEnum.CLIENT_ERROR.getMsg(),e);
        }
        return resultResponse;

    }

    /**
     * 服务端发送消息
     * @param message
     * @return
     * @throws InterruptedException
     */
    @PostMapping("sendMessage")
    @ApiOperation("测试发送消息接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "message",value = "报文"),
                      @ApiImplicitParam(name = "testContent",value = "测试说明")})
    public  ResultResponse<Boolean> sendMessage(String message,String testContent) {
        System.out.println("*****开始测试 ->"+testContent+"*****");
        ResultResponse<Boolean> resultResponse=new ResultResponse<>();
        try {
            //入参检查
            if(!StringUtils.isEmpty(message)&&!StringUtils.isEmpty(testContent)){
                resultResponse.setSuccessData( minaServer.sendConMessage(message));
            }else{
                resultResponse.setError(RunStatusEnum.ARG_NULL_ERROR);
                log.error(RunStatusEnum.ARG_NULL_ERROR.getMsg());
            }
        }catch (Exception e){
            resultResponse.setError(RunStatusEnum.SEND_MESSAGE_ERROR);
            log.error(RunStatusEnum.SEND_MESSAGE_ERROR.getMsg(),e);
        }
        return resultResponse;
    }

    /**
     * 应答消息
     * @return
     */
    @GetMapping("returnMessage")
    @ApiOperation("测试返回应答消息接口")
    public  ResultResponse<String> returnMessage(){
        ResultResponse<String> resultResponse=new ResultResponse<>();
        try {
            String message=minaServer.returnMessage();
            resultResponse.setSuccessData(message);
            System.out.println("应答消息："+message);
        }catch (Exception e){
            resultResponse.setError(RunStatusEnum.RETURN_DATA_ERROR);
            log.error(RunStatusEnum.RETURN_DATA_ERROR.getMsg(),e);
        }
        return resultResponse;
    }

    /**
     * 获取客户数量
     * @return
     */
    @ApiOperation("获取用户数量接口")
    @GetMapping("getClientNum")
    public  ResultResponse<Integer> getClientNum(){
        ResultResponse<Integer> resultResponse=new ResultResponse<>();
        try {
            resultResponse.setSuccessData(minaServer.getConNum());
        }catch (Exception e){
            resultResponse.setError(RunStatusEnum.CLIENT_NUM_ERROR);
            log.error(RunStatusEnum.CLIENT_NUM_ERROR.getMsg(),e );
        }
        return resultResponse;
    }


}
