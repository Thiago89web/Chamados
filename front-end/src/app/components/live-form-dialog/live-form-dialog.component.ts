import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { LiveService } from 'src/app/services/live.service';
import * as moment from 'moment';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-live-form-dialog',
  templateUrl: './live-form-dialog.component.html',
  styleUrls: ['./live-form-dialog.component.css']
})
export class LiveFormDialogComponent implements OnInit {
   liveForm: any;

  constructor(
    public dialogRef: MatDialogRef<LiveFormDialogComponent>,
    private fb: FormBuilder,
    private rest: LiveService,
    private toast: ToastrService) { }

  ngOnInit(): void {
    this.liveForm = this.fb.group({
      liveName: ['', [Validators.required]],
      channelName: ['', [Validators.required]],
      liveLink: ['', [Validators.required]]
    });
  }

  createLive(){
    this.rest.postLives(this.liveForm.value).subscribe(result => {
      this.toast.success('Vídeo cadastrado com sucesso!', 'Sucesso');
      this.dialogRef.close(true);
      this.liveForm.reset();
      window.location.reload();

    });
    
  }

  cancel(){
    this.dialogRef.close(true);
    this.liveForm.reset();
  }

}
