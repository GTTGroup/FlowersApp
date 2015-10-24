package com.do1.flowersapp.activity;

import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.do1.flowersapp.R;
import com.do1.flowersapp.business.listener.ReturnGoodsListener;
import com.do1.flowersapp.common.BaseRecyclerViewAdapter;
import com.do1.flowersapp.context.BaseActivity;
import com.do1.flowersapp.tools.ViewHolder;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bruce Too
 * On 10/23/15.
 * At 00:22
 * 异常退款，一键下单
 */
public class OddReturnGoodActivity extends BaseActivity implements ReturnGoodsListener {

    @Bind(R.id.recycler_view)
    RecyclerView mRecylerView;

    private String mReturnIllustrate;//退款说明

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odd_return_good);
        ButterKnife.bind(this);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
        mRecylerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = (int) getResources().getDimension(R.dimen.return_goods_item_decoration);
            }
        });
        mRecylerView.setAdapter(new ReturnGoodsAdapter(this));

    }

    @OnClick(R.id.btn_top_back)
    public void onTopBack() {
        finish();
    }

    /**
     * 选择按钮
     */
    @OnClick(R.id.text_choose)
    public void onTopChoose() {
        Toast.makeText(this, "选择", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_commit)
    public void onCommit() {
        Toast.makeText(this, "提交", Toast.LENGTH_SHORT).show();
    }

    //界面点击的接口
    @Override
    public void onReturnTypeClick(int position) {
        //打开弹层选择 退货类型
        Toast.makeText(this, "点击退货类型", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddBtnClick() {
        //打开相册界面 刷新footerView
        Toast.makeText(this, "打开相册选择", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReturnIllustrate(String info) {
        mReturnIllustrate = info;
        Toast.makeText(this, "退货说明:"+info, Toast.LENGTH_SHORT).show();
    }
    //界面点击的接口


    class ReturnGoodsAdapter extends BaseRecyclerViewAdapter {
       private ReturnGoodsListener listener;
        public ReturnGoodsAdapter(ReturnGoodsListener listener){
            this.listener = listener;
        }

        public void updateFooterView() {

        }

        @Override
        public boolean useFooter() {
            return true;
        }

        @Override
        public RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_odd_return_item_add, parent, false);
            return new FooterViewHolder(view);
        }

        @Override
        public void onBindFooterView(final RecyclerView.ViewHolder holder, int position) {
            final FooterViewHolder viewHolder = (FooterViewHolder) holder;
            viewHolder.editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                     listener.onReturnIllustrate(s.toString());
                }
            });
            //@TODO 可能要自动计算图片的个数来指定gridView高度
            viewHolder.gridView.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return 4;
                }

                @Override
                public Object getItem(int position) {
                    return position;
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    ViewHolder holder1 = ViewHolder.newInstance(parent.getContext(), convertView, parent, R.layout.grid_item_photo);
                    SimpleDraweeView image = holder1.getView(R.id.image_add);
                    if (position == 0) {//第一个添加+button
                        Uri uri = new Uri.Builder()
                                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME) // "res"
                                .path(String.valueOf(R.drawable.icon_upload))
                                .build();
                        image.setImageURI(uri);
                        image.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //选择相片
                                listener.onAddBtnClick();
                            }
                        });
                    } else {
//                          Uri uri = new Uri.Builder()
//                                  .scheme(UriUtil.LOCAL_FILE_SCHEME) // "file"
//                                  .path("path")
//                                  .build();
                        image.setImageURI(Uri.parse("http://pic7.nipic.com/20100424/4271569_235714000888_2.jpg"));
                    }
                    return holder1.getView();
                }
            });
        }

        @Override
        public RecyclerView.ViewHolder onCreateBasicItemViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_odd_return_item, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindBasicItemView(RecyclerView.ViewHolder holder, final int position) {
            final ItemViewHolder viewHolder = (ItemViewHolder) holder;

            //count字段应该是选择界面传过来的 保存在List中 这里需要改变list的值
            viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = Integer.valueOf(viewHolder.count.getText().toString());
                    viewHolder.count.setText(String.valueOf(count + 1));
                }
            });
            viewHolder.btnSub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = Integer.valueOf(viewHolder.count.getText().toString());
                    if (count > 0) {
                        viewHolder.count.setText(String.valueOf(count - 1));
                    }
                }
            });

            viewHolder.draweeCover.setImageURI(Uri.parse("http://pic7.nipic.com/20100424/4271569_235714000888_2.jpg"));
            viewHolder.desc.setText("红色 A及 多头 单价:￥35");
            viewHolder.name.setText("极品玫瑰新鲜大方");
            viewHolder.layoutGoods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //弹出层选择退款类型
                  listener.onReturnTypeClick(position);
                }
            });
        }

        @Override
        public int getBasicItemCount() {
            return 2;
        }

        @Override
        public int getBasicItemType(int position) {
            return 0;
        }

        class FooterViewHolder extends RecyclerView.ViewHolder {
            @Bind(R.id.grid_view)
            GridView gridView;
            @Bind(R.id.edit_desc)
            EditText editText;

            public FooterViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {
            @Bind(R.id.drawee_cover)
            SimpleDraweeView draweeCover;
            @Bind(R.id.text_name)
            TextView name;
            @Bind(R.id.text_desc)
            TextView desc;
            @Bind(R.id.image_sub)
            ImageView btnSub;
            @Bind(R.id.image_add)
            ImageView btnAdd;
            @Bind(R.id.text_count)
            TextView count;
            @Bind(R.id.layout_return_goods)
            View layoutGoods;

            public ItemViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }


        @Override
        public boolean useHeader() {
            return false;
        }

        @Override
        public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindHeaderView(RecyclerView.ViewHolder holder, int position) {

        }

    }
}
