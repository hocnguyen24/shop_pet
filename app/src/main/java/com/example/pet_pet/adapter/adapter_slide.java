package com.example.pet_pet.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.pet_pet.Model.Slideiten;
import com.example.pet_pet.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;


public class adapter_slide extends RecyclerView.Adapter<adapter_slide.SlideViewHoler> {
    private List<Slideiten> slideItems;
    private ViewPager2 viewPager2;

    public adapter_slide(List<Slideiten> slideItems, ViewPager2 viewPager2) {
        this.slideItems = slideItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SlideViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlideViewHoler(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item_anh, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SlideViewHoler holder, int position) {
        Slideiten slideiten=slideItems.get(position);
        if (slideiten==null){
            return;
        }

        holder.imgView.setImageResource(slideiten.getImage());
//        holder.setImgView(slideItems.get(position));
//        ///vòng lặp cho slide quay về
        viewPager2.post(runnable);

    }

    @Override
    public int getItemCount() {
        if (slideItems !=null){
            return slideItems.size();
        }
        return 0;
    }

    class SlideViewHoler extends RecyclerView.ViewHolder {
        private ImageView imgView;

        SlideViewHoler(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imageslide);
        }


    }
    //vòng lặp
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
//            slideItems.addAll(slideItems);
//            notifyDataSetChanged();

            int vitri = viewPager2.getCurrentItem();
            if (vitri == slideItems.size() -1){
                viewPager2.setCurrentItem(0);
            }else {
                 viewPager2.setCurrentItem(vitri+1);
            }
        }
    };

}
