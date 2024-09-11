package rpc;

import com.alibaba.fastjson.JSON;
import com.jd.lbs.geocode.api.GeocodingService;
import com.jd.lbs.geocode.api.dto.GisPointDto;
import com.jd.lbs.presort.api.dto.BaseResponse;
import com.jd.lbs.presort.api.dto.GisPresortRequestDto;
import com.jd.lbs.presort.api.dto.GisPresortResponseDto;
import com.jd.lbs.presort.api.dto.common.CommonRequestDto;
import com.jd.lbs.presort.api.dto.common.CommonResultDto;
import com.jd.lbs.presort.api.dto.fenceattached.FenceAttachedQueryParams;
import com.jd.lbs.presort.api.dto.fenceattached.FenceAttachedResult;
import com.jd.lbs.presort.api.service.AccuratePresortExternalService;
import com.jd.lbs.presort.api.service.CommonPresortService;
import com.jd.lbs.presort.api.service.fenceattached.FenceAttachedService;
import com.jd.mrd.b.common.constant.InterfaceConstant;
import com.jd.mrd.b.common.exception.BusinessException;
import com.jd.mrd.b.common.exception.address.AddressExceptionCodeConst;
import com.jd.mrd.b.common.exception.address.AddressResolveException;
import com.jd.mrd.b.rpc.convert.GisConvert;
import com.jd.mrd.b.rpc.sdk.dto.address.AddressGisPointDto;
import com.jd.mrd.b.rpc.sdk.dto.address.AddressInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @ClassName: GisPresortRpc
 * @Description:
 * @Author liwei50@jd.com
 * @Date 2021/3/1 5:15 下午
 */
@Slf4j
@Component
public class GisPresortRpc {

    @Value("#{configProperties['geocodingService.geo.appId']}")
    private String appId;

    @Resource
    AccuratePresortExternalService accuratePresortExternalService;
    @Resource
    GeocodingService geocodingService;

    /**
     * 通用预分拣接口
     */
    @Resource
    private CommonPresortService commonPresortService;

    /**
     * 快递入仓接口
     */
    @Resource
    private FenceAttachedService fenceAttachedService;

    /**
     * 查询快递入仓信息
     * https://joyspace.jd.com/page/TUH6v083sc3YKekkgzIf
     *
     * @param params
     * @return
     */
    public FenceAttachedResult getBusinessTypes(String appKey, FenceAttachedQueryParams params) {
        return new ThrowExceptionRpcCallInvoker<>(new ThrowExceptionRpcCall<BaseResponse<FenceAttachedResult>, FenceAttachedResult>() {
            @Override
            public BusinessException throwException(BaseResponse<FenceAttachedResult> response) {
                return new AddressResolveException(AddressExceptionCodeConst.PRE_STATION_CODE);
            }

            @Override
            public BaseResponse<FenceAttachedResult> call() throws Exception {
                log.info("method {} input:{}", methodName(), JSON.toJSONString(params));
                return fenceAttachedService.getBusinessTypes(appKey, params);
            }

            @Override
            public String methodName() {
                return "com.jd.mrd.b.rpc.gis.GisPresortRpc.getBusinessTypes";
            }

            @Override
            public boolean filter(BaseResponse<FenceAttachedResult> response) {
                return response != null && response.getResult() != null && response.getStatusCode() != null && response.getStatusCode() == 200;
            }

            @Override
            public FenceAttachedResult convert(BaseResponse<FenceAttachedResult> response) {
                return response.getResult();
            }
        }).invoke();
    }

    /**
     * 通过“通用预分拣接口”获取到站点编码和路区编码
     * https://joyspace.jd.com/page/TAPzu7rIm9Hpm4btBWQf
     *
     * @param addressInfo
     * @return
     */
    public CommonResultDto presort(String appKey, AddressInfo addressInfo) {
        CommonRequestDto commonRequestDto = GisConvert.convertAddressVOToCommonRequestDto(addressInfo);
        return new ThrowExceptionRpcCallInvoker<>(new ThrowExceptionRpcCall<BaseResponse<CommonResultDto>, CommonResultDto>() {
            @Override
            public BusinessException throwException(BaseResponse<CommonResultDto> response) {
                return new AddressResolveException(AddressExceptionCodeConst.PRE_STATION_CODE);
            }

            @Override
            public BaseResponse<CommonResultDto> call() throws Exception {
                log.info("method {} input:{}", methodName(), JSON.toJSONString(commonRequestDto));
                return commonPresortService.presort(appKey, commonRequestDto);
            }

            @Override
            public String methodName() {
                return "com.jd.mrd.b.rpc.gis.GisPresortRpc.presort";
            }

            @Override
            public boolean filter(BaseResponse<CommonResultDto> response) {
                return response != null && response.getResult() != null;
            }

            @Override
            public CommonResultDto convert(BaseResponse<CommonResultDto> response) {
                return response.getResult();
            }
        }).invoke();
    }

    /**
     * 外单预分拣服务接口
     * https://cf.jd.com/pages/viewpage.action?pageId=196427311
     *
     * @param addressInfo
     * @return
     */
    public GisPresortResponseDto presortExternalOrder(AddressInfo addressInfo) {
        GisPresortRequestDto gisPresortRequestDto = GisConvert.convertAddressVOToGisPresortRequestDto(addressInfo);
        return new ThrowExceptionRpcCallInvoker<>(new ThrowExceptionRpcCall<BaseResponse<GisPresortResponseDto>, GisPresortResponseDto>() {
            @Override
            public BusinessException throwException(BaseResponse<GisPresortResponseDto> response) {
                return new AddressResolveException(AddressExceptionCodeConst.PRE_STATION_CODE);
            }

            @Override
            public BaseResponse<GisPresortResponseDto> call() throws Exception {
                log.info("method {} input:{}", methodName(), JSON.toJSONString(addressInfo));
                return accuratePresortExternalService.presortExternalOrder(gisPresortRequestDto);
            }

            @Override
            public String methodName() {
                return "com.jd.mrd.b.rpc.gis.GisPresortRpc.presortExternalOrder";
            }

            @Override
            public boolean filter(BaseResponse<GisPresortResponseDto> response) {
                return response != null && response.getResult() != null && response.getResult().getStationCode() != null && response.getResult().getStationCode() > 1;
            }

            @Override
            public GisPresortResponseDto convert(BaseResponse<GisPresortResponseDto> response) {
                return response.getResult();
            }
        }).invoke();
    }

    public com.jd.lbs.geocode.api.base.BaseResponse<GisPointDto> geoBase(String address) {
        return new ThrowExceptionRpcCallInvoker<>(new ThrowExceptionRpcCall<com.jd.lbs.geocode.api.base.BaseResponse<GisPointDto>, com.jd.lbs.geocode.api.base.BaseResponse<GisPointDto>>() {
            @Override
            public com.jd.lbs.geocode.api.base.BaseResponse<GisPointDto> call() throws Exception {
                return geocodingService.geo(appId, address);
            }

            @Override
            public String methodName() {
                return "com.jd.mrd.b.rpc.gis.GisPresortRpc.geo";
            }

            @Override
            public boolean filter(com.jd.lbs.geocode.api.base.BaseResponse<GisPointDto> response) {
                return response != null && response.getStatus() == InterfaceConstant.SUCCESS_200 && response.getResult() != null;
            }

            @Override
            public com.jd.lbs.geocode.api.base.BaseResponse<GisPointDto> convert(com.jd.lbs.geocode.api.base.BaseResponse<GisPointDto> response) {
                return response;
            }

            @Override
            public BusinessException throwException(com.jd.lbs.geocode.api.base.BaseResponse<GisPointDto> response) {
                return new BusinessException(AddressExceptionCodeConst.COORDIANTE_RESOLVE);
            }
        }).invoke();
    }

    public AddressGisPointDto geo(String address) {
        return new ThrowExceptionRpcCallInvoker<>(new ThrowExceptionRpcCall<com.jd.lbs.geocode.api.base.BaseResponse<GisPointDto>, AddressGisPointDto>() {

            @Override
            public com.jd.lbs.geocode.api.base.BaseResponse<GisPointDto> call() {
                log.info("call {} method param appId={}, address={}", this.methodName(), appId, address);
                return geocodingService.geo(appId, address);
            }

            @Override
            public String methodName() {
                return "com.jd.mrd.jdexpress.rpc.address.AddressServiceRpc.geo";
            }

            @Override
            public boolean filter(com.jd.lbs.geocode.api.base.BaseResponse<GisPointDto> baseResponse) {
                return Objects.nonNull(baseResponse) && Objects.nonNull(baseResponse.getResult()) && baseResponse.getStatus() == 200;
            }

            @Override
            public AddressGisPointDto convert(com.jd.lbs.geocode.api.base.BaseResponse<GisPointDto> baseResponse) {
                return GisConvert.handleAddressGisPointBo(baseResponse.getResult());
            }

            @Override
            public BusinessException throwException(com.jd.lbs.geocode.api.base.BaseResponse<GisPointDto> baseResponse) {
                return new BusinessException(AddressExceptionCodeConst.COORDIANTE_RESOLVE);
            }
        }).invoke();
    }
}
