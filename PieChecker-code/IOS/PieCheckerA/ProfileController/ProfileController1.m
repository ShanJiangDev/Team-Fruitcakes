//
//  ProfileController1.m
//  ADVFlatUI
//
//  Created by Tope on 31/05/2013.
//  Copyright (c) 2013 App Design Vault. All rights reserved.
//  This is a template for another project

#import "ProfileController1.h"
#import <QuartzCore/QuartzCore.h>

@interface ProfileController1 ()

@end

@implementation ProfileController1

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	
    UIColor* mainColor = [UIColor colorWithRed:222.0/255 green:59.0/255 blue:47.0/255 alpha:1.0f];
    
    NSString* fontName = @"GillSans-Italic";
    NSString* boldFontName = @"GillSans-Bold";
    
    self.nameLabel.textColor =  [UIColor whiteColor];
    self.nameLabel.font =  [UIFont fontWithName:boldFontName size:18.0f];
    self.nameLabel.text = @"Ultime Piemaker";
    
    self.locationLabel.textColor =  [UIColor whiteColor];
    self.locationLabel.font =  [UIFont fontWithName:fontName size:14.0f];
    self.locationLabel.text = @"Pie Land";
    
    UIFont* labelFont = [UIFont fontWithName:boldFontName size:14.0f];
    
    self.joinedLabel.textColor =  mainColor;
    self.joinedLabel.font =  labelFont;
    self.joinedLabel.text = @"Joined";
    
    self.bioLabel.textColor =  mainColor;
    self.bioLabel.font =  labelFont;
    self.bioLabel.text = @"Bio";
    
    self.friendLabel.textColor =  mainColor;
    self.friendLabel.font =  labelFont;
    self.friendLabel.text = @"Friends";
    
    UIFont* valueFont = [UIFont fontWithName:fontName size:14.0f];
    
    self.joinedValueLabel.textColor =  mainColor;
    self.joinedValueLabel.font =  valueFont;
    self.joinedValueLabel.text = @"1 Year Ago";
    
    self.bioValueLabel.textColor =  mainColor;
    self.bioValueLabel.font =  valueFont;
    self.bioValueLabel.text = @"I bake pies";
    
    
    self.profileBgImageView.image = [UIImage imageNamed:@"WomanWithPie.jpg"];
    self.profileBgImageView.contentMode = UIViewContentModeScaleAspectFill;
    
    self.profileImageView.image = [UIImage imageNamed:@"pie.jpg"];
    self.profileImageView.contentMode = UIViewContentModeScaleAspectFill;
    self.profileImageView.clipsToBounds = YES;
    self.profileImageView.layer.cornerRadius = 48.0f;
    self.profileImageView.layer.borderWidth = 4.0f;
    self.profileImageView.layer.borderColor = [UIColor whiteColor].CGColor;
    
    self.overlayView.backgroundColor = [UIColor colorWithWhite:0.0f alpha:0.5f];
    
    self.countContainer.backgroundColor = [UIColor colorWithWhite:0.95 alpha:1.0f];
    
    self.joinedContainer.layer.borderColor = [UIColor colorWithWhite:0.9 alpha:0.7].CGColor;
    self.joinedContainer.layer.borderWidth = 1.0f;
    
    self.bioContainer.layer.borderColor = [UIColor colorWithWhite:0.9 alpha:0.7].CGColor;
    self.bioContainer.layer.borderWidth = 1.0f;
    
    self.friendContainer.layer.borderColor = [UIColor colorWithWhite:0.9 alpha:0.7].CGColor;
    self.friendContainer.layer.borderWidth = 1.0f;
    
    UIColor* imageBorderColor = [UIColor colorWithRed:222.0/255 green:59.0/255 blue:47.0/255 alpha:0.4f];
    
    [self styleFriendProfileImage:self.friendImageView1 withImageNamed:@"HappyPie.jpg" andColor:imageBorderColor];
 

    UIScrollView* scrollView = (UIScrollView*)self.view;
    
    scrollView.contentSize = CGSizeMake(320, 590);
}

-(void)styleFriendProfileImage:(UIImageView*)imageView withImageNamed:(NSString*)imageName andColor:(UIColor*)color{
    
    imageView.image = [UIImage imageNamed:imageName];
    imageView.contentMode = UIViewContentModeScaleAspectFill;
    imageView.clipsToBounds = YES;
    imageView.layer.cornerRadius = 30.0f;
    imageView.layer.borderWidth = 4.0f;
    imageView.layer.borderColor = color.CGColor;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
