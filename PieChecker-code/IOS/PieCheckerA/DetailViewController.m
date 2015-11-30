//
//  VideoController.h
//  PieCheckerA
//
//  Created by Philip Malm on 27/05/14.
//  Copyright (c) 2014 FruitCakes. All rights reserved.
//

#import "DetailViewController.h"

@implementation DetailViewController

@synthesize productImageView;
@synthesize productName;

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Release any cached data, images, etc that aren't in use.
}

#pragma mark - View lifecycle

- (void)viewDidLoad
{
    [super viewDidLoad];

    self.title = productName;
    
    NSString *imageName = [NSString stringWithFormat:@"%@.jpg", productName];
    productImageView.image = [UIImage imageNamed:imageName];  
    
    UIRotationGestureRecognizer *rotationGesture = [[UIRotationGestureRecognizer alloc] initWithTarget:self action:@selector(rotateImage:)];
    [self.view addGestureRecognizer:rotationGesture];
    
    UIPinchGestureRecognizer *pinchGesture = [[UIPinchGestureRecognizer alloc] initWithTarget:self action:@selector(scaleImage:)];
    [self.view addGestureRecognizer:pinchGesture];
    
    UITapGestureRecognizer *tapGesture = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(resetImage:)];
    [self.view addGestureRecognizer:tapGesture];
    
    UIPanGestureRecognizer *panGesture = [[UIPanGestureRecognizer alloc] initWithTarget:self action:@selector(moveImage:)];
    [panGesture setMinimumNumberOfTouches:1];
	[panGesture setMaximumNumberOfTouches:1];
    [self.view addGestureRecognizer:panGesture];
}

- (void)viewDidUnload
{
    [self setProductImageView:nil];
    [self setProductImageView:nil];
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
}

- (void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
}

- (void)viewWillDisappear:(BOOL)animated
{
	[super viewWillDisappear:animated];
}

- (void)viewDidDisappear:(BOOL)animated
{
	[super viewDidDisappear:animated];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    // Return YES for supported orientations
    return (interfaceOrientation != UIInterfaceOrientationPortraitUpsideDown);
}

#pragma mark - Gestures

- (void)rotateImage:(UIRotationGestureRecognizer *)recognizer 
{
    
	if([recognizer state] == UIGestureRecognizerStateEnded) {
        
		previousRotation = 0.0;
		return;
	}
    
	CGFloat newRotation = 0.0 - (previousRotation - [recognizer rotation]);
    
	CGAffineTransform currentTransformation = productImageView.transform;
	CGAffineTransform newTransform = CGAffineTransformRotate(currentTransformation, newRotation);
    
    productImageView.transform = newTransform;
    
	previousRotation = [recognizer rotation];
}

- (void)scaleImage:(UIPinchGestureRecognizer *)recognizer 
{
    
	if([recognizer state] == UIGestureRecognizerStateEnded) {
        
		previousScale = 1.0;
		return;
	}
    
	CGFloat newScale = 1.0 - (previousScale - [recognizer scale]);
    
	CGAffineTransform currentTransformation = productImageView.transform;
	CGAffineTransform newTransform = CGAffineTransformScale(currentTransformation, newScale, newScale);
    
    productImageView.transform = newTransform;
    
	previousScale = [recognizer scale];
}

- (void)resetImage:(UITapGestureRecognizer *)recognizer 
{
    [UIView beginAnimations:nil context:nil];
    [UIView setAnimationDuration:0.3];
    
    productImageView.transform = CGAffineTransformIdentity;
    
    [productImageView setCenter:CGPointMake(self.view.frame.size.width/2, self.view.frame.size.height/2)];
    
    [UIView commitAnimations];
}

- (void)moveImage:(UIPanGestureRecognizer *)recognizer 
{
    CGPoint newCenter = [recognizer translationInView:self.view];
    
	if([recognizer state] == UIGestureRecognizerStateBegan) {
        
		beginX = productImageView.center.x; 
		beginY = productImageView.center.y;
	}
    
	newCenter = CGPointMake(beginX + newCenter.x, beginY + newCenter.y);
    
	[productImageView setCenter:newCenter];
    
}


@end
