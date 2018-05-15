package com.yuqi.admin.py.wxapi;

		import android.app.Activity;
		import android.content.Intent;
		import android.os.Bundle;
		import android.widget.Toast;

		import com.tencent.mm.opensdk.constants.ConstantsAPI;
		import com.tencent.mm.opensdk.modelbase.BaseReq;
		import com.tencent.mm.opensdk.modelbase.BaseResp;
		import com.tencent.mm.opensdk.openapi.IWXAPI;
		import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
		import com.tencent.mm.opensdk.openapi.WXAPIFactory;
		import com.yuqi.admin.py.activity.SShoppingCartActivity;
		import com.yuqi.admin.py.activity.ShouyActivity;
		import com.yuqi.admin.py.data.CommonData;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {


	private IWXAPI api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.pay_result);

		api = WXAPIFactory.createWXAPI(this, CommonData.APP_ID);
		api.handleIntent(getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	protected void onPause() {
		super.onPause();
		overridePendingTransition(0,0);
	}

	@Override
	public void onResp(BaseResp resp) {

		//判断状态
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			if (resp.errCode == 0) {
				Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(WXPayEntryActivity.this,SShoppingCartActivity.class);
				startActivity(intent);
				finish();
			} else if (resp.errCode == -2) {
				Toast.makeText(this, "用户取消", Toast.LENGTH_SHORT).show();
//				Intent intent = new Intent(WXPayEntryActivity.this,SShoppingCartActivity.class);
//				startActivity(intent);
//				finish();
			} else {//-1的状态
				Toast.makeText(this, "支付失败,请重试", Toast.LENGTH_SHORT).show();
			}
		}
		//记得关闭
		finish();
	}
}