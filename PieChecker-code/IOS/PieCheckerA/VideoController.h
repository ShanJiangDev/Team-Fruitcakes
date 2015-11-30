//
//  VideoController.h
//  PieCheckerA
//
//  Created by Philip Malm on 27/05/14.
//  Copyright (c) 2014 FruitCakes. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface VideoController : UIViewController <UIWebViewDelegate>
@property (strong, nonatomic) IBOutlet UIWebView *video;
@property (weak, nonatomic) IBOutlet UIActivityIndicatorView *myIndicator;
@property (weak, nonatomic) IBOutlet UILabel *myLabel;


@end
