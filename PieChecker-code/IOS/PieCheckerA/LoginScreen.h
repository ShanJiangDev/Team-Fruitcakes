//
//  LoginScreen.h
//  PieCheckerA
//
//  Created by Philip Malm on 27/05/14.
//  Copyright (c) 2014 FruitCakes. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface LoginScreen : UIViewController <UITextFieldDelegate>

@property (weak, nonatomic) IBOutlet UITextField *txtUsername;

@property (weak, nonatomic) IBOutlet UITextField *txtPassword;

- (IBAction)loginClicked:(id)sender;
- (IBAction)backgroundTap:(id)sender;

@end
