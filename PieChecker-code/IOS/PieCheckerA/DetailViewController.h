//
//  VideoController.h
//  PieCheckerA
//
//  Created by Philip Malm on 27/05/14.
//  Copyright (c) 2014 FruitCakes. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DetailViewController : UIViewController
{
    NSString *productName;
    
    CGFloat previousScale;
    CGFloat previousRotation;
    
    CGFloat beginX;
    CGFloat beginY;
}
@property (strong, nonatomic) IBOutlet UIImageView *productImageView;
@property (strong, nonatomic) NSString *productName;

@end
