package com.redapesolutions.sns;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONException;

import android.util.Log;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreatePlatformEndpointRequest;
import com.amazonaws.services.sns.model.CreatePlatformEndpointResult;

public class AmazonSNS extends CordovaPlugin{
  private String publicationArn = "arn:aws:sns:us-east-1:170714312040:app/GCM/AstroView";
  private String accessKey = "AKIAJ62G2V54XQZXOZRQ";
  private String secretKey = "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";
  private AmazonSNSClient client;
  private String uploadAction = "create";
  private String LOG_TAG = AmazonSNS.class.getName();
  
  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    // TODO Auto-generated method stub
    AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
    client = new AmazonSNSClient(awsCredentials);
    super.initialize(cordova, webView);
  }
  
  @Override
  public boolean execute(String action, CordovaArgs args,
      CallbackContext callbackContext) throws JSONException {
    // TODO Auto-generated method stub
    
    if (uploadAction.equals(action)) {
      String registrationId = args.getString(0);
      CreatePlatformEndpointRequest platformEndpointRequest = new CreatePlatformEndpointRequest();
      platformEndpointRequest.setToken(registrationId);
      platformEndpointRequest.setPlatformApplicationArn(publicationArn);
      CreatePlatformEndpointResult result = client.createPlatformEndpoint(platformEndpointRequest);
      Log.i(LOG_TAG, result.toString());
      callbackContext.success();
      return true;
    }
    
    return false;
  }
  
}
