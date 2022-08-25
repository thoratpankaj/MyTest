package com.mytest.example.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.mytest.example.R
import com.mytest.example.data.model.Data
import com.mytest.example.databinding.ListGroupBinding
import com.mytest.example.databinding.ListItemBinding


class ExpandableCovidListAdapter(
    private val context: Context,
    private val listDataGroup :List<String>,
    private val listDataTeams : HashMap<String, List<Data>>
): BaseExpandableListAdapter(){

    var mContext: Context? = context

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private lateinit var groupBinding: ListGroupBinding
    private lateinit var itemBinding: ListItemBinding

    override fun getGroupCount(): Int {
        return listDataGroup.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return this.listDataTeams.get(this.listDataGroup.get(groupPosition))?.size!!
    }

    override fun getGroup(groupPosition: Int): Any {
        return listDataGroup.get(groupPosition)
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return this.listDataTeams.get(this.listDataGroup.get(groupPosition))?.get(childPosition)!!
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(listPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {

        var row:View? = convertView


        if(row==null){
            row = inflater.inflate(R.layout.list_group,null)
        }

        val listTitle = getGroup(listPosition) as String

        row?.findViewById<TextView>(R.id.listTitle)?.text = listTitle

        return row!!

    }

    override fun getChildView(groupPosition: Int, childPosition: Int,
                              isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {



        var row:View? = convertView
        row = inflater.inflate(R.layout.list_item, null)

        var item:Data = getChild(groupPosition,childPosition) as Data

        if(null!=item){
            row?.findViewById<TextView>(R.id.expandedListItem)?.text = item.iso
            row?.findViewById<TextView>(R.id.expandedSubListItem)?.text = item.name
        }
        return row!!

    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }

}