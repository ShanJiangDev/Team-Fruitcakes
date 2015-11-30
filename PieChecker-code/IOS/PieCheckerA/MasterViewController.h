//
//  VideoController.h
//  PieCheckerA
//
//  Created by Philip Malm on 27/05/14.
//  Copyright (c) 2014 FruitCakes. All rights reserved.
//

#import <UIKit/UIKit.h>

#import "DetailViewController.h"

@interface MasterViewController : UITableViewController
{
    NSArray *list;
}

@property (strong, nonatomic) NSArray *list;

@end
