package tool;

import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;


/**
 * 压缩和解压缩工具类
 *
 * @author yangfq 2019年6月29日
 */
public class CompressUtils {

    /**
     * 压缩
     *
     * @param data
     * @return
     */
    public static String compressData(String data) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DeflaterOutputStream zos = new DeflaterOutputStream(bos);
            zos.write(data.getBytes());
            zos.close();
            return getenBASE64inCodec(bos.toByteArray());
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    private static String getenBASE64inCodec(byte[] b) {
        if (b == null) {
            return null;
        }

        return DigestUtil.base64encode(b, Base64.DEFAULT);
    }

    /**
     * 解压缩
     *
     * @param encdata
     * @return
     */
    public static String decompressData(String encdata) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            InflaterOutputStream zos = new InflaterOutputStream(bos);
            zos.write(getdeBASE64inCodec(encdata));
            zos.close();
            return new String(bos.toByteArray());
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    private static byte[] getdeBASE64inCodec(String s) {
        if (s == null) {
            return null;
        }
        return DigestUtil.base64decode(s, Base64.DEFAULT);
    }
	public static void main(String[] args) {
		String p = "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";
		System.out.println(decompressData(p));
		
//		String q ="{\"cashierTime\":0,\"createTime\":0,\"buyerCreditAmount\":0,\"buyerName\":\"\",\"buyerPhone\":\"\",\"buyerUid\":0,\"chargeOrderFree\":0,\"createOrderUid\":0,\"orderItemLst\":[{\"basketUnitPrice\":0,\"batchId\":45419,\"batchItemId\":252112,\"buyerName\":\"01老李\",\"buyerUid\":46584,\"batchCarNo\":\"2\",\"extend5\":0,\"extend2\":\"ed87068ca7304ea8bd72ae4ff52b04db\",\"goodsAttrId\":252112,\"goodsAttrName\":\"西红柿\",\"goodsFieldsList\":[{\"key\":\"saleNumber\",\"unit\":\"件\",\"value\":\"1\"},{\"key\":\"unitPrice\",\"unit\":\"元/件\",\"value\":\"2\"},{\"key\":\"unitTradeFee\",\"unit\":\"元/件\",\"value\":\"5\"},{\"key\":\"unitBillFee\",\"unit\":\"元\",\"value\":\"1\"},{\"key\":\"isIncludeTrade\",\"unit\":\" \",\"value\":\"false\"},{\"key\":\"packWeight\",\"value\":\"50\"}],\"goodsId\":252112,\"goodsName\":\"西红柿\",\"isIncludeTrade\":false,\"orderType\":\"1\",\"originSortBy\":0,\"packKind\":1,\"packWeight\":50,\"promotion\":false,\"replenishFlag\":false,\"supplierId\":\"85083\",\"supplierName\":\"老羊\",\"baseObjId\":0}],\"handId\":0,\"todayOrder\":false,\"marketId\":\"1\",\"operaWatermark\":0,\"orderCreateTime\":0,\"orderSaleAmount\":0,\"orderSaleNumber\":0,\"orderStatus\":0,\"orderType\":\"1\",\"originOrderNo\":\"\",\"originUUID\":\"a9f4bc90-d0ea-43ce-95e6-c5d6646daa6e\",\"payMethod\":0,\"payStatus\":0,\"remark\":\"\",\"sourceType\":0,\"stallId\":\"10005\",\"statusWatermark\":0,\"todayNo\":0,\"updateTime\":0,\"validateStock\":true}"
//		System.out.println(compressData(q));
	}

}
