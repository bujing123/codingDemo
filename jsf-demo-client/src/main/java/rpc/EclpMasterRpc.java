package rpc;

import com.alibaba.fastjson.JSON;
import com.jd.eclp.master.dict.domain.Dictionary;
import com.jd.eclp.master.dict.service.DictionaryService;
import com.jd.etms.framework.utils.cache.annotation.Cache;
import com.jd.etms.framework.utils.cache.keygenerator.ArgsMatchCacheKeyGenerator;
import com.jd.mrd.b.common.constant.BasicConstants;
import com.jd.mrd.b.common.exception.BusinessException;
import com.jd.mrd.b.common.exception.eclp.EclpBusinessCodeConst;
import com.jd.mrd.b.rpc.sdk.dto.eclp.DictionaryDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: EclpMasterRpc
 * @Description:
 * @Author liwei50@jd.com
 * @Date 2021/3/3 4:46 下午
 */
@Slf4j
@Component
public class EclpMasterRpc {

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 根据类型编号查询数据字典
     *
     * @param dictType
     * @return
     * @Doc https://cf.jd.com/pages/viewpage.action?pageId=238370633
     */
    @Cache(key = "rpc.eclp.EclpMasterRpc.findDictListByType-@args0", memoryEnable = false, keyGenerator = ArgsMatchCacheKeyGenerator.class, redisExpiredTime = BasicConstants.CACHE_TWO_MINUTE)
    public List<DictionaryDTO> findDictListByType(String dictType) {
        return new ThrowExceptionRpcCallInvoker<>(new ThrowExceptionRpcCall<List<Dictionary>, List<DictionaryDTO>>() {
            @Override
            public List<Dictionary> call() {
                return dictionaryService.findDictListByType(dictType);
            }

            @Override
            public String methodName() {
                return "com.jd.mrd.jdexpress.rpc.eclp.impl.EclpMasterServiceRpcImpl.findDictListByType";
            }

            @Override
            public boolean filter(List<Dictionary> dictionaries) {
                return CollectionUtils.isNotEmpty(dictionaries);
            }

            @Override
            public List<DictionaryDTO> convert(List<Dictionary> dictionaries) {
                List<DictionaryDTO> dictionaryBoList = dictionaries.stream().map(dictionary -> {
                    DictionaryDTO dictionaryBo = new DictionaryDTO();
                    dictionaryBo.setParamValue(dictionary.getParamValue());
                    dictionaryBo.setParamNameDes(dictionary.getParamNameDes());
                    return dictionaryBo;
                }).collect(Collectors.toList());
                log.info("EclpMasterServiceRpcImpl.findDictListByType#convert to DictionaryBo:{}", JSON.toJSONString(dictionaryBoList));
                return dictionaryBoList;
            }

            @Override
            public BusinessException throwException(List<Dictionary> dictionaries) {
                return new BusinessException(EclpBusinessCodeConst.ECLP_MASTER_DICT);
            }
        }).invoke();
    }

    /**
     * 根据类型编号查询数据字典
     *
     * @param dictType
     * @return
     */
    @Cache(key = "rpc.eclp.EclpMasterRpc.findDictByCode-@args0-@args1", memoryEnable = false, keyGenerator = ArgsMatchCacheKeyGenerator.class, redisExpiredTime = BasicConstants.CACHE_TWO_MINUTE)
    public DictionaryDTO findDictByCode(String dictType, String code) {
        return new FeedbackRpcCallInvoker<>(new FeedbackRpcCall<Dictionary, DictionaryDTO>() {
            @Override
            public DictionaryDTO feedback() {
                return null;
            }

            @Override
            public Dictionary call() {
                return dictionaryService.findDictByCode(dictType, code);
            }

            @Override
            public String methodName() {
                return "com.jd.mrd.jdexpress.rpc.eclp.impl.EclpMasterServiceRpcImpl.findDictByCode";
            }

            @Override
            public boolean filter(Dictionary dictionary) {
                return dictionary != null;
            }

            @Override
            public DictionaryDTO convert(Dictionary dictionary) {
                if (dictionary == null) {
                    return null;
                }
                DictionaryDTO dictionaryBo = new DictionaryDTO();
                dictionaryBo.setParamValue(dictionary.getParamValue());
                dictionaryBo.setParamNameDes(dictionary.getParamNameDes());
                return dictionaryBo;
            }
        }).invoke();
    }
}
