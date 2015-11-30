//
//  VideoController.m
//  PieCheckerA
//
//  Created by Philip Malm on 27/05/14.
//  Copyright (c) 2014 FruitCakes. All rights reserved.
//

#import "VideoController.h"

@interface VideoController ()

@end

@implementation VideoController
@synthesize video = _video;

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    self.video.delegate = self;
    
    NSURL *url = [NSURL URLWithString:@"http://www.twitch.tv/luuktv"];
    NSURLRequest *requestURL = [NSURLRequest requestWithURL:url];
    [self.video loadRequest:requestURL];
    //http://www.twitch.tv/luuktv
}

- (void)webViewDidStartLoad:(UIWebView *)webView {
    [_myIndicator startAnimating];
    _myLabel.hidden = FALSE;
}

- (void)webViewDidFinishLoad:(UIWebView *)webView {
    [_myIndicator stopAnimating];
    _myLabel.hidden = TRUE;
}

- (void)viewDidUnload
{
    [self setVideo:nil];
    [super viewDidUnload];
    // Release any retained subviews of the main view.
}

#pragma mark - Optional UIWebViewDelegate delegate methods
- (BOOL)webView:(UIWebView *)webView shouldStartLoadWithRequest:(NSURLRequest *)request navigationType:(UIWebViewNavigationType)navigationType
{
    return YES;
}

- (void)videoDidStartLoad:(UIWebView *)video
{
    [UIApplication sharedApplication].networkActivityIndicatorVisible = YES;
}

- (void)videoDidFinishLoad:(UIWebView *)video
{
    [UIApplication sharedApplication].networkActivityIndicatorVisible = NO;
}

- (void)webView:(UIWebView *)webView didFailLoadWithError:(NSError *)error
{
    [UIApplication sharedApplication].networkActivityIndicatorVisible = NO;
}





@end
