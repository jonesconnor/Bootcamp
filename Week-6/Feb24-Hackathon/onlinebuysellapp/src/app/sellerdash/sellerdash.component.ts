import { AddProductDialogComponent } from './../add-product-dialog/add-product-dialog.component';
import { ProducthandlerService } from './../producthandler.service';
import { MatDialog } from '@angular/material/dialog';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { User } from '../models/User';

@Component({
  selector: 'app-sellerdash',
  templateUrl: './sellerdash.component.html',
  styleUrls: ['./sellerdash.component.css']
})
export class SellerdashComponent implements OnInit{

  currentUser!: User;

  constructor(public dialog: MatDialog, private producthandler: ProducthandlerService, private auth: AuthService) {

  }

  ngOnInit(): void {
    this.auth.currentUser.subscribe(user => {
      this.currentUser = user;
    })
  }

  openDialog() {
    const dialogRef = this.dialog.open(AddProductDialogComponent, {
      width: '500px'
    });

    dialogRef.afterClosed().subscribe(
      (res) => {
        let product = {
          name: res.name,
          description: res.description,
          price: res.price,
          sellerId: this.currentUser.id
        };
        this.producthandler.addProduct(product).subscribe(
          (result: any) => {
            console.log(result);
          },
          (err: any) => {
            console.log(err)
          }
        );
      }
    );
  }
}
